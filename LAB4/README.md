# LAB 4 - Khảo sát và đánh giá bề mặt mạng bằng Nmap

## 1. Thông tin sinh viên
- Họ và tên: Nguyễn Ngọc Tú Vy
- MSSV: 1150070050
- Môn học: An toàn hệ thống thông tin
- Bài thực hành: LAB 4 - Khảo sát và đánh giá bề mặt mạng bằng Nmap

## 2. Môi trường thực hành
- Máy ảo hóa: VMware Workstation
- Kali Linux: Kali Linux 2026.2
- Máy quét: Kali Linux
- Máy đích: Metasploitable 2
- Kiểu mạng: Host-only
- Kali IP: 192.168.239.131/24
- Metasploitable 2 IP: 192.168.239.130/24

## 3. Mục tiêu
- Xác định các host đang hoạt động trong mạng Host-only.
- Khảo sát các cổng TCP/UDP.
- So sánh các kỹ thuật TCP Connect, SYN, FIN, Xmas, NULL và ACK scan.
- Nhận diện dịch vụ và phiên bản bằng Nmap.
- Nhận diện hệ điều hành.
- Thực hiện một số NSE script trên dịch vụ SMB.
- Xuất kết quả quét để lưu hồ sơ bằng chứng.

## 4. Các tình huống đã thực hiện

### 4.1. Kiểm tra kết nối
Kali Linux và Metasploitable 2 được cấu hình cùng mạng Host-only.

Kết quả:
- Kali: 192.168.239.131/24
- Metasploitable 2: 192.168.239.130/24
- Ping: PASS, 0% packet loss

### 4.2. Host Discovery
Đã thực hiện host discovery trên dải mạng 192.168.239.0/24.

Kết quả:
- 192.168.239.1
- 192.168.239.130 - Metasploitable 2
- 192.168.239.131 - Kali Linux
- 192.168.239.254

Trạng thái: PASS

### 4.3. TCP Connect Scan
Kết quả:
- 23 cổng open
- 977 cổng closed
- 0 cổng filtered

Trạng thái: PASS

### 4.4. SYN Scan
Kết quả:
- 23 cổng open
- 977 cổng closed
- 0 cổng filtered

Trạng thái: PASS

### 4.5. FIN / Xmas / NULL Scan
Các kỹ thuật FIN, Xmas và NULL cho kết quả tương tự:
- 23 cổng open|filtered
- 977 cổng closed

Lưu ý: open|filtered không đồng nghĩa chắc chắn cổng đang mở.

Trạng thái: PASS

### 4.6. ACK Scan
Kết quả:
- 1000 cổng unfiltered

ACK scan được dùng để quan sát chính sách lọc, không dùng để khẳng định cổng open.

Trạng thái: PASS

### 4.7. UDP Scan
Quét 20 UDP port phổ biến.

Kết quả nổi bật:
- 53/udp open - domain
- 137/udp open - netbios-ns
- Một số cổng ở trạng thái open|filtered.

Trạng thái: PASS

### 4.8. Service Version Detection
Một số dịch vụ phát hiện được:
- 21/tcp - vsftpd 2.3.4
- 22/tcp - OpenSSH 4.7p1
- 80/tcp - Apache httpd 2.2.8
- 445/tcp - Samba
- 3306/tcp - MySQL 5.0.51a
- 5432/tcp - PostgreSQL
- 5900/tcp - VNC
- 6667/tcp - UnrealIRCd
- 8180/tcp - Apache Tomcat

Trạng thái: PASS

### 4.9. OS Detection
Nmap nhận diện:
- Device type: general purpose
- Running: Linux 2.6.X
- OS details: Linux 2.6.9 - 2.6.33
- Network Distance: 1 hop

Trạng thái: PASS

### 4.10. NSE SMB
NSE smb-os-discovery phát hiện:
- OS: Unix
- Samba: 3.0.20-Debian
- Computer name: metasploitable
- Domain: localdomain
- FQDN: metasploitable.localdomain

Kiểm tra smb-vuln-ms17-010 không trả về trạng thái VULNERABLE, vì vậy không đủ cơ sở kết luận mục tiêu dễ bị ảnh hưởng hoặc đã an toàn.

Trạng thái: PASS

## 5. Lỗi gặp phải và cách khắc phục

### Lỗi 1: Quên mật khẩu Kali
Cách khắc phục:
- Khởi động vào GRUB.
- Chỉnh boot entry để vào root shell.
- Đặt lại mật khẩu tài khoản kali.
- Khởi động lại VM.

Kết quả: Khắc phục thành công.

### Lỗi 2: Gõ nhầm ipconfig trên Metasploitable
Nguyên nhân:
Metasploitable là Linux nên không sử dụng lệnh ipconfig của Windows.

Cách khắc phục:
Sử dụng ifconfig.

Kết quả: Khắc phục thành công.

## 6. Kết quả
Môi trường Host-only hoạt động ổn định. Kali có thể phát hiện, quét và thu thập thông tin từ Metasploitable 2. Các kỹ thuật Nmap trong phạm vi LAB4 đã được thực hiện thành công.

## 7. Tài liệu minh chứng
Ảnh chụp và file output được lưu trong:
- `images/`
- `output/`
