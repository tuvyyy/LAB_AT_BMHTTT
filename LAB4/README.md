# LAB 4 - Khảo sát và đánh giá bề mặt mạng bằng Nmap

## Thông tin sinh viên
- Họ và tên: Nguyễn Ngọc Tú Vy
- MSSV: 1150070050
- Lớp: 11_DH_TMDT
- Môn học: An toàn hệ thống thông tin

## Môi trường thực hành
- VMware Workstation
- Kali Linux 2026.2
- Metasploitable 2
- Nmap 7.99
- Mạng thực hành: Host-only
- Kali Linux: 192.168.239.131/24
- Metasploitable 2: 192.168.239.130/24

## Cách dựng môi trường
1. Import Kali Linux và Metasploitable 2 vào VMware Workstation.
2. Cấu hình cả hai máy ảo sử dụng Host-only.
3. Metasploitable 2 chỉ sử dụng Host-only, không sử dụng Bridged.
4. Xác định IP thực tế của từng VM.
5. Kiểm tra kết nối Kali -> Metasploitable 2 trước khi thực hiện Nmap.

## Các tình huống đã thực hiện

### Host Discovery
- Quét mạng Host-only.
- Phát hiện 4 host đang hoạt động.
- Trạng thái: PASS.

### TCP Connect Scan
- 23 cổng open.
- 977 cổng closed.
- Trạng thái: PASS.

### SYN Scan
- 23 cổng open.
- 977 cổng closed.
- Trạng thái: PASS.

### FIN / Xmas / NULL Scan
- Các cổng dịch vụ xuất hiện ở trạng thái open|filtered.
- Không đồng nhất open|filtered với open.
- Trạng thái: PASS.

### ACK Scan
- 1000 cổng được xác định unfiltered.
- ACK scan được dùng để quan sát chính sách lọc.
- Trạng thái: PASS.

### UDP Scan
- 53/udp: open - domain.
- 137/udp: open - netbios-ns.
- Một số cổng khác ở trạng thái open|filtered.
- Trạng thái: PASS.

### Service Version Detection
Một số dịch vụ phát hiện được:
- FTP: vsftpd 2.3.4
- SSH: OpenSSH 4.7p1
- HTTP: Apache httpd 2.2.8
- SMB: Samba
- MySQL: 5.0.51a
- PostgreSQL
- VNC
- UnrealIRCd
- Apache Tomcat

Trạng thái: PASS.

### OS Detection
- Device type: general purpose.
- Running: Linux 2.6.X.
- OS details: Linux 2.6.9 - 2.6.33.
- Network Distance: 1 hop.
- Trạng thái: PASS.

### NSE SMB
smb-os-discovery phát hiện:
- OS: Unix
- Samba: 3.0.20-Debian
- Computer name: metasploitable
- Domain: localdomain

Kiểm tra smb-vuln-ms17-010 không trả về trạng thái VULNERABLE.
Không đủ bằng chứng để kết luận hệ thống dễ bị ảnh hưởng hoặc đã được vá.

Trạng thái: PASS.

## Output
Các file kết quả Nmap:
- `ket_qua.txt`
- `ket_qua.xml`
- `smb.txt`

## Lỗi gặp phải và cách khắc phục

### Quên mật khẩu Kali
Đã sử dụng GRUB để vào môi trường khôi phục và đặt lại mật khẩu tài khoản Kali.

Kết quả: PASS.

### Gõ nhầm lệnh ipconfig trên Metasploitable 2
Metasploitable 2 sử dụng Linux nên lệnh phù hợp là `ifconfig`.

Kết quả: PASS.

## Ghi chú an toàn
Toàn bộ hoạt động quét được thực hiện trong mạng VMware Host-only trên các máy ảo thuộc môi trường thực hành của sinh viên. Không thực hiện quét hệ thống bên ngoài.
