# LAB5 - THIẾT LẬP TƯỜNG LỬA PFSENSE

**Môn:** An toàn hệ thống thông tin  
**Sinh viên:** Nguyễn Ngọc Tú Vy  
**MSSV:** 1150070050  
**Lớp:** 11_DH_TMDT  
**Ngày thực hành:** 06/10/2026  
**Nền tảng ảo hóa:** VMware Workstation  
**Firewall:** pfSense CE 2.7.2-RELEASE (amd64)

---

## 1. Mục tiêu

LAB5 triển khai hệ thống tường lửa **pfSense** với ba vùng mạng riêng biệt:

- **WAN**: kết nối ra Internet.
- **LAN**: mạng nội bộ.
- **DMZ**: vùng dành cho máy chủ cung cấp dịch vụ.

Bài thực hành tập trung vào:

- Cấu hình WAN - LAN - DMZ.
- Kiểm tra routing và Internet.
- Automatic Outbound NAT.
- Firewall Rules.
- Cô lập DMZ khỏi LAN.
- NAT Port Forward.
- Firewall Logging.
- Kiểm thử thực tế từ nhiều máy trong mô hình.

---

## 2. Mô hình mạng

| Thành phần | Địa chỉ / Network | Vai trò |
|---|---|---|
| Máy thật Windows | `192.168.1.6/24` | Host quản trị và kiểm thử |
| VMware VMnet8 | `192.168.195.0/24` | WAN / NAT |
| VMware VMnet2 | `10.0.0.0/8` | LAN |
| VMware VMnet3 | `172.16.0.0/16` | DMZ |
| pfSense WAN | `192.168.195.131/24` | Kết nối WAN |
| pfSense LAN | `10.0.0.1/8` | Gateway LAN / WebGUI |
| pfSense DMZ | `172.16.0.1/16` | Gateway DMZ |
| Windows Server LAN | `10.0.0.2/8` | Host LAN / DC theo mô hình |
| Ubuntu LAN-Test | `10.0.0.3/8` | Máy kiểm thử LAN |
| Windows Lab / DMZ-Web | `172.16.0.2/16` | IIS Web Server trong DMZ |

### Sơ đồ logic

```text
                       Internet
                           |
                    VMware VMnet8
                           |
                 WAN 192.168.195.131
                      +---------+
                      | pfSense |
                      +---------+
                       /       \
                      /         \
             LAN 10.0.0.1     DMZ 172.16.0.1
                  |                  |
               VMnet2             VMnet3
                  |                  |
        +---------+---------+        |
        |                   |        |
   10.0.0.2            10.0.0.3   172.16.0.2
 Windows Server       Ubuntu Test   DMZ-Web/IIS
