#!/bin/bash
set -euo pipefail

echo "========================================="
echo "CONFIGURING SQUID, E2GUARDIAN, CLAMAV"
echo "Date: $(date)"
echo "========================================="

# 1. Configure Squid
echo "[1/5] Configuring Squid..."
cp /etc/squid/squid.conf /etc/squid/squid.conf.bak 2>/dev/null || true
cat << 'EOF' > /etc/squid/squid.conf
# LAB7 Squid configuration
http_port 127.0.0.1:3128
http_access allow localhost
http_access deny all

# KB10 - Strict object size limit (100 MB)
reply_body_max_size 100 MB all

coredump_dir /var/spool/squid
access_log daemon:/var/log/squid/access.log squid
EOF

squid -k parse
systemctl restart squid

# 2. Configure E2Guardian Lists & Template
echo "[2/5] Configuring E2Guardian Lists & Block Template..."
cat << 'EOF' > /etc/e2guardian/custom_block_template.html
<!doctype html><html lang="vi"><head><meta charset="utf-8"><title>LAB7 - Truy cập bị chặn</title>
<style>body{font-family:Arial,sans-serif;background:#f7f9fb;margin:0}.box{max-width:760px;margin:70px auto;background:white;padding:32px;border-left:8px solid #b3261e;box-shadow:0 4px 20px #0002}h1{color:#b3261e}.meta{background:#eef3f8;padding:14px;margin:15px 0}.btn{display:inline-block;padding:10px 20px;background:#2f5597;color:white;text-decoration:none;border-radius:4px;font-weight:bold}</style></head>
<body><div class="box"><h1>Truy cập bị chặn theo chính sách LAB 7</h1>
<p>Yêu cầu web không phù hợp với policy đang áp dụng.</p>
<div class="meta">
<strong>URL:</strong> -URL-<br>
<strong>Lý do:</strong> -REASONGIVEN-<br>
<strong>Danh mục:</strong> -CATEGORIES-<br>
<strong>Client IP:</strong> -IP-
</div>
<div style="margin-top:20px;">
<a href="-BYPASS-" class="btn">Xác nhận cảnh báo và Mở tạm thời (5 phút)</a>
</div>
<p style="color:#666;font-size:12px;margin-top:20px;">LAB 7 - pfSense Web Protection System</p></div></body></html>
EOF

# Hard block + Warning sites in bannedsitelist
cat << 'EOF' > /etc/e2guardian/lists/bannedsitelist
china.lab7.test
mideast.lab7.test
jobs.lab7.test
blockedgame.lab7.test
weapons.lab7.test
politics.lab7.test
religion.lab7.test
streaming.lab7.test
EOF

# Hard block ONLY in domainsnobypass
cat << 'EOF' > /etc/e2guardian/lists/domainsnobypass
china.lab7.test
mideast.lab7.test
jobs.lab7.test
blockedgame.lab7.test
EOF

# Exception / Whitelist in exceptionsitelist
cat << 'EOF' > /etc/e2guardian/lists/exceptionsitelist
normal.lab7.test
education.lab7.test
download.lab7.test
EOF

# 3. Configure E2Guardian main config & Filter Group 1
echo "[3/5] Configuring E2Guardian configs..."
cp /etc/e2guardian/e2guardian.conf /etc/e2guardian/e2guardian.conf.bak 2>/dev/null || true
cp /etc/e2guardian/e2guardianf1.conf /etc/e2guardian/e2guardianf1.conf.bak 2>/dev/null || true

# Update e2guardian.conf settings
sed -i 's/^filterports =.*/filterports = 8080/' /etc/e2guardian/e2guardian.conf
sed -i 's/^proxyip =.*/proxyip = 127.0.0.1/' /etc/e2guardian/e2guardian.conf
sed -i 's/^proxyport =.*/proxyport = 3128/' /etc/e2guardian/e2guardian.conf
sed -i 's/^loglevel =.*/loglevel = 2/' /etc/e2guardian/e2guardian.conf
sed -i "s|^#*contentscanner = '/etc/e2guardian/contentscanners/clamdscan.conf'|contentscanner = '/etc/e2guardian/contentscanners/clamdscan.conf'|" /etc/e2guardian/e2guardian.conf

# Bind to 0.0.0.0 to accept both LAN 10.6.0.30 and localhost testing
sed -i 's/^filterip =.*/#filterip =/' /etc/e2guardian/e2guardian.conf

# Update e2guardianf1.conf settings
sed -i 's/^reportinglevel =.*/reportinglevel = 3/' /etc/e2guardian/e2guardianf1.conf
sed -i 's/^bypass =.*/bypass = 300/' /etc/e2guardian/e2guardianf1.conf
sed -i 's/^contentscanexceptions =.*/contentscanexceptions = off/' /etc/e2guardian/e2guardianf1.conf
sed -i "s|^htmltemplate =.*|htmltemplate = '/etc/e2guardian/custom_block_template.html'|" /etc/e2guardian/e2guardianf1.conf

# Permissions for ClamAV integration
usermod -a -G clamav e2guardian || true
usermod -a -G e2guardian clamav || true
chmod 755 /etc/e2guardian/custom_block_template.html

# 4. Remove NAT and finalize LAN configuration (10.6.0.30/24)
echo "[4/5] Removing NAT adapter and enforcing LAN configuration..."
cat << 'EOF' > /etc/netplan/01-netcfg.yaml
network:
  version: 2
  renderer: networkd
  ethernets:
    ens33:
      dhcp4: false
      addresses:
        - 10.6.0.30/24
      routes:
        - to: default
          via: 10.6.0.1
      nameservers:
        addresses: [10.6.0.10, 10.6.0.1, 8.8.8.8]
EOF
chmod 600 /etc/netplan/01-netcfg.yaml
netplan apply
ip addr del 172.16.6.10/24 dev ens33 2>/dev/null || true
ip link set dev ens37 down 2>/dev/null || true

# 5. Restart and verify all proxy services
echo "[5/5] Restarting services and verifying listening ports..."
systemctl restart clamav-daemon
systemctl restart squid
systemctl restart e2guardian

echo "=== SERVICES STATUS ==="
echo -n "Squid: " && systemctl is-active squid
echo -n "E2Guardian: " && systemctl is-active e2guardian
echo -n "ClamAV daemon: " && systemctl is-active clamav-daemon

echo "=== LISTENING PORTS ==="
ss -tulpn | grep -E '3128|8080|clamd'

echo "Configuration completed successfully!"
