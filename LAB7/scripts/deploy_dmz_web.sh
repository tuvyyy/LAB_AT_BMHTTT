#!/bin/bash
set -euo pipefail

echo "========================================="
echo "LAB7 DMZ-WEB DEPLOYMENT & VERIFICATION"
echo "Date: $(date)"
echo "========================================="

# 1. Install packages
echo "[1/5] Updating apt and installing nginx, curl, openssh-server..."
apt-get update
DEBIAN_FRONTEND=noninteractive apt-get install -y nginx curl openssh-server

# 2. Deploy LAB7 Virtual Hosts and Test Files
echo "[2/5] Deploying LAB7 Virtual Hosts & Mock Websites..."
ROOT=/var/www/lab7
mkdir -p "$ROOT"

make_site() {
  name="$1"; title="$2"; text="$3"
  mkdir -p "$ROOT/$name"
  cat <<EOF > "$ROOT/$name/index.html"
<!doctype html><html lang="vi"><head><meta charset="utf-8"><title>$title</title>
<style>body{font-family:Arial,sans-serif;max-width:900px;margin:60px auto;line-height:1.5} .tag{padding:8px 12px;background:#eef3f8;border-left:5px solid #2f5597}</style></head>
<body><h1>$title</h1><div class="tag">LAB 7 - Website mo phong noi bo</div><p>$text</p></body></html>
EOF
  cat <<EOF > "/etc/nginx/sites-available/$name.lab7.test"
server {
  listen 80;
  server_name $name.lab7.test;
  root $ROOT/$name;
  index index.html;
  location / { try_files \$uri \$uri/ =404; }
}
EOF
  ln -sf "/etc/nginx/sites-available/$name.lab7.test" "/etc/nginx/sites-enabled/$name.lab7.test"
}

rm -f /etc/nginx/sites-enabled/default
make_site normal "Normal Site" "Website duoc phep truy cap binh thuong."
make_site china "China-Sim Site" "Website mo phong nhom dia ly Trung Quoc de kiem thu policy."
make_site mideast "MiddleEast-Sim Site" "Website mo phong nhom dia ly Trung Dong de kiem thu policy."
make_site jobs "Job Search Site" "Website mo phong nhom tim viec."
make_site weapons "Weapons Warning Site" "Website mo phong nhom can canh bao."
make_site politics "Politics Warning Site" "Website mo phong nhom can canh bao."
make_site religion "Religion Warning Site" "Website mo phong nhom can canh bao."
make_site education "Education Site" "Website giao duc luon duoc phep."
make_site entertainment "Entertainment Site" "Website giai tri de kiem thu gio truy cap."
make_site games "Games Site" "Website game de kiem thu gio truy cap."
make_site streaming "Streaming Site" "Website streaming mo phong - che do canh bao."
make_site blockedgame "Blocked Game Site" "Website game bi chan hoan toan."
make_site download "Download Test Site" "Thu muc nay chua file kich thuoc va EICAR test."

mkdir -p "$ROOT/download/files"
dd if=/dev/zero of="$ROOT/download/files/10MB.bin" bs=1M count=10 status=none
dd if=/dev/zero of="$ROOT/download/files/100MB.bin" bs=1M count=100 status=none
dd if=/dev/zero of="$ROOT/download/files/110MB.bin" bs=1M count=110 status=none

cat <<'EOF' > "$ROOT/download/index.html"
<!doctype html><html><head><meta charset="utf-8"><title>LAB7 Download Test</title></head>
<body><h1>LAB7 Download Test</h1><ul>
<li><a href="/files/10MB.bin">10MB.bin</a></li>
<li><a href="/files/100MB.bin">100MB.bin</a></li>
<li><a href="/files/110MB.bin">110MB.bin</a></li>
<li><a href="/files/eicar.com.txt">eicar.com.txt (benign antivirus test string)</a></li>
</ul></body></html>
EOF

python3 - <<'PY2' > "$ROOT/download/files/eicar.com.txt"
parts=["X5O!P%@AP[4\\PZX54(P^)","7CC)7}$EICAR-STANDARD-","ANTIVIRUS-TEST-FILE!$H+H*"]
print(''.join(parts))
PY2

nginx -t
systemctl enable --now nginx
systemctl reload nginx

# 3. Configure OpenSSH
echo "[3/5] Configuring OpenSSH..."
systemctl enable --now ssh
systemctl is-active ssh

# 4. Verification of All 13 Sites
echo "[4/5] Testing all 13 Virtual Hosts..."
sites=("normal" "china" "mideast" "jobs" "weapons" "politics" "religion" "education" "entertainment" "games" "streaming" "blockedgame" "download")
all_passed=true

for s in "${sites[@]}"; do
  code=$(curl -s -o /dev/null -w "%{http_code}" -H "Host: $s.lab7.test" http://127.0.0.1/)
  if [ "$code" = "200" ]; then
    echo "  [PASS] $s.lab7.test -> HTTP $code"
  else
    echo "  [FAIL] $s.lab7.test -> HTTP $code"
    all_passed=false
  fi
done

# 5. Verify Test Files
echo "[5/5] Verifying test files in /var/www/lab7/download/files/..."
ls -lh "$ROOT/download/files/"
head -c 20 "$ROOT/download/files/eicar.com.txt"; echo "..."

echo "========================================="
if [ "$all_passed" = true ]; then
  echo "ALL 13 LAB7 WEBSITES & FILES DEPLOYED SUCCESSFULLY (PASS)"
else
  echo "DEPLOYMENT COMPLETED WITH ERRORS"
fi
echo "========================================="
