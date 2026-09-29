# LAB 4 - KHẢO SÁT VÀ ĐÁNH GIÁ BỀ MẶT MẠNG BẰNG NMAP

## 1. Thông tin sinh viên

- Họ và tên: Nguyễn Ngọc Tú Vy
- MSSV: 1150070050
- Lớp: 11_DH_TMDT
- Môn học: An toàn hệ thống thông tin
- Bài thực hành: LAB 4 - Khảo sát và đánh giá bề mặt mạng bằng Nmap
- Ngày thực hành: 29/09/2026

## 2. Video và báo cáo

- Video YouTube: [CẬP NHẬT LINK VIDEO]
- GitHub Public: https://github.com/tuvyyy/LAB_AT_BMHTTT
- Thư mục LAB4: https://github.com/tuvyyy/LAB_AT_BMHTTT/tree/main/LAB4
- Báo cáo: `11_DH_TMDT-LAB4_1150070050-NguyenNgocTuVy.docx`

---

## 3. Mục tiêu thực hành

LAB4 được thực hiện nhằm:

- Dựng môi trường mạng ảo cô lập bằng VMware Host-only.
- Xác định đúng địa chỉ IP của Kali Linux và Metasploitable 2.
- Phát hiện các host đang hoạt động trong mạng.
- Khảo sát các cổng TCP và UDP.
- So sánh các kỹ thuật TCP Connect, SYN, FIN, Xmas, NULL và ACK scan.
- Nhận diện dịch vụ và phiên bản bằng Nmap.
- Fingerprint hệ điều hành bằng OS Detection.
- Sử dụng Aggressive Scan để tổng hợp thông tin.
- Sử dụng NSE script để thu thập thông tin SMB và kiểm tra MS17-010.
- Xuất kết quả Nmap thành nhiều định dạng phục vụ lưu trữ bằng chứng.
- Thực hiện một số bài tập bổ sung để mở rộng quá trình khảo sát.

---

## 4. Môi trường thực hành

| Thành phần | Phiên bản / cấu hình | Vai trò |
|---|---|---|
| VMware Workstation | Workstation 17.x | Nền tảng ảo hóa |
| Kali Linux | 2026.2 | Máy quét |
| Nmap | 7.99 | Công cụ khảo sát mạng |
| Metasploitable 2 | Linux 2.6.x | Máy đích thực hành |
| Network | VMware Host-only | Mạng cô lập |
| Subnet | 192.168.239.0/24 | Mạng LAB4 |
| Kali IP | 192.168.239.131/24 | Máy quét |
| Metasploitable IP | 192.168.239.130/24 | Máy đích |

---

## 5. Cách dựng môi trường

1. Import Kali Linux và Metasploitable 2 vào VMware Workstation.
2. Cấu hình Metasploitable 2 chỉ sử dụng mạng Host-only.
3. Kali Linux được cấu hình Host-only trong giai đoạn thực hành.
4. Xác định địa chỉ IP thực tế của từng VM.
5. Kiểm tra kết nối giữa Kali và Metasploitable 2.
6. Chỉ bắt đầu quét sau khi xác nhận hai máy cùng subnet và kết nối thành công.

### Kết quả kiểm tra mạng

- Kali Linux: `192.168.239.131/24`
- Metasploitable 2: `192.168.239.130/24`
- Ping Kali -> Metasploitable 2:
  - 4 packets transmitted
  - 4 received
  - 0% packet loss

**Trạng thái: PASS**

---

## 6. Các tình huống đã thực hiện

### 6.1. Host Discovery

Thực hiện host discovery trên mạng:

`192.168.239.0/24`

Phát hiện 4 host đang hoạt động:

| IP | Vai trò |
|---|---|
| 192.168.239.1 | VMware host adapter |
| 192.168.239.130 | Metasploitable 2 |
| 192.168.239.131 | Kali Linux |
| 192.168.239.254 | VMware network service / DHCP |

**Trạng thái: PASS**

---

### 6.2. TCP Connect Scan (-sT)

Kết quả:

- Open: 23
- Closed: 977
- Filtered: 0
- Thời gian: 4.65 giây

Một số dịch vụ được phát hiện:

- 21/tcp - FTP
- 22/tcp - SSH
- 23/tcp - Telnet
- 80/tcp - HTTP
- 445/tcp - SMB
- 3306/tcp - MySQL
- 5432/tcp - PostgreSQL
- 5900/tcp - VNC
- 6667/tcp - IRC
- 8180/tcp - HTTP/Tomcat

**Trạng thái: PASS**

---

### 6.3. SYN Scan (-sS)

Kết quả:

- Open: 23
- Closed: 977
- Filtered: 0
- Thời gian: 4.71 giây

Kết quả cho thấy TCP Connect Scan và SYN Scan phát hiện cùng tập cổng mở trong môi trường Host-only.

SYN Scan cần quyền cao hơn vì sử dụng raw packet, trong khi TCP Connect Scan sử dụng cơ chế `connect()` của hệ điều hành.

**Trạng thái: PASS**

---

### 6.4. FIN / Xmas / NULL Scan

Kết quả:

| Kỹ thuật | Open\|Filtered | Closed | Thời gian |
|---|---:|---:|---:|
| FIN (-sF) | 23 | 977 | 5.85 s |
| Xmas (-sX) | 23 | 977 | 5.86 s |
| NULL (-sN) | 23 | 977 | 5.85 s |

Trạng thái `open|filtered` không được hiểu là chắc chắn cổng đang mở.

Nmap không thể phân biệt giữa:

- cổng mở nhưng không phản hồi;
- hoặc gói thăm dò bị bộ lọc/firewall làm im lặng.

**Trạng thái: PASS**

---

### 6.5. ACK Scan (-sA)

Kết quả:

- 1000 cổng `unfiltered`
- Thời gian: 4.70 giây

ACK Scan không dùng để xác định cổng open/closed mà chủ yếu dùng để quan sát chính sách lọc.

**Trạng thái: PASS**

---

### 6.6. UDP Scan

Quét 20 UDP port phổ biến.

Kết quả:

- 2 cổng open
- 9 cổng open|filtered
- 9 cổng closed
- Thời gian: 12.43 giây

Một số kết quả nổi bật:

| Port | State | Service |
|---|---|---|
| 53/udp | open | domain |
| 137/udp | open | netbios-ns |
| 67/udp | open\|filtered | dhcps |
| 69/udp | open\|filtered | tftp |
| 445/udp | open\|filtered | microsoft-ds |
| 123/udp | closed | ntp |

UDP scan thường khó xác định trạng thái hơn TCP vì không có cơ chế bắt tay.

**Trạng thái: PASS**

---

## 7. Nhận diện dịch vụ và phiên bản (-sV)

Một số dịch vụ được Nmap nhận diện:

| Port | Service | Version |
|---|---|---|
| 21/tcp | FTP | vsftpd 2.3.4 |
| 22/tcp | SSH | OpenSSH 4.7p1 Debian 8ubuntu1 |
| 23/tcp | Telnet | Linux telnetd |
| 25/tcp | SMTP | Postfix smtpd |
| 53/tcp | DNS | ISC BIND 9.4.2 |
| 80/tcp | HTTP | Apache httpd 2.2.8 |
| 445/tcp | SMB | Samba smbd 3.X - 4.X |
| 2121/tcp | FTP | ProFTPD 1.3.1 |
| 3306/tcp | MySQL | MySQL 5.0.51a-3ubuntu5 |
| 5432/tcp | PostgreSQL | PostgreSQL 8.3.0 - 8.3.7 |
| 5900/tcp | VNC | VNC protocol 3.3 |
| 6667/tcp | IRC | UnrealIRCd |
| 8180/tcp | HTTP | Apache Tomcat/Coyote JSP engine 1.1 |

Thời gian quét: 57.01 giây.

Version Detection cung cấp thông tin chi tiết hơn việc chỉ biết số cổng, giúp quản trị viên có cơ sở kiểm tra phiên bản, cấu hình và bản vá.

**Trạng thái: PASS**

---

## 8. OS Detection (-O)

Kết quả:

- Device type: general purpose
- Running: Linux 2.6.X
- OS CPE: `cpe:/o:linux:linux_kernel:2.6`
- OS details: Linux 2.6.9 - 2.6.33
- Network Distance: 1 hop
- Thời gian: 6.01 giây

OS Detection chỉ là kết quả fingerprint và không nên được xem là tuyệt đối.

**Trạng thái: PASS**

---

## 9. Aggressive Scan (-A)

Aggressive Scan cung cấp:

- Service Version Detection
- OS Detection
- Default NSE Scripts
- Traceroute

Thời gian thực tế: 169.63 giây.

So sánh:

| Scan | Nội dung chính | Thời gian |
|---|---|---:|
| -O | OS fingerprint | 6.01 s |
| -sV | Service/version | 57.01 s |
| -A | OS + version + scripts + traceroute | 169.63 s |

Aggressive Scan cung cấp nhiều thông tin hơn nhưng đồng thời sinh nhiều probe và lưu lượng hơn.

**Trạng thái: PASS**

---

## 10. NSE - SMB

### smb-os-discovery

Kết quả:

- 445/tcp: open
- OS: Unix
- Samba: 3.0.20-Debian
- Computer name: metasploitable
- Domain: localdomain
- FQDN: metasploitable.localdomain

**Trạng thái: PASS**

### smb-vuln-ms17-010

Cổng 445/tcp mở nhưng script không trả về dòng `VULNERABLE` và cũng không đưa ra kết luận xác định.

Vì vậy không đủ bằng chứng để kết luận:

- hệ thống dễ bị ảnh hưởng;
- hoặc hệ thống đã được vá/an toàn.

**Trạng thái: KHÔNG XÁC ĐỊNH**

---

## 11. Xuất kết quả Nmap

Các file output chính:

| File | Định dạng | Mục đích |
|---|---|---|
| `ket_qua.txt` | Normal text | Đọc trực tiếp và dùng trong báo cáo |
| `ket_qua.xml` | XML | Dữ liệu có cấu trúc |
| `smb.txt` | Grepable | Lọc nhanh kết quả SMB |

Ba file được tạo trong cùng phiên thực hành ngày 29/09/2026.

**Trạng thái: PASS**

---

# 12. Bài tập bổ sung

## 12.1. So sánh -sT, -sS và -sA

| Kỹ thuật | Open | Closed | Filtered | Unfiltered | Thời gian |
|---|---:|---:|---:|---:|---:|
| -sT | 23 | 977 | 0 | - | 4.65 s |
| -sS | 23 | 977 | 0 | - | 4.71 s |
| -sA | N/A | N/A | 0 | 1000 | 4.70 s |

`-sT` và `-sS` thống nhất về tập cổng open/closed.

`-sA` trả lời câu hỏi khác về chính sách lọc nên không được quy đổi thành số cổng open/closed.

**Trạng thái: PASS**

---

## 12.2. Quét toàn bộ 65535 TCP ports

Thực hiện full TCP port scan trên Metasploitable 2.

Kết quả:

- 65535 TCP ports được khảo sát
- 30 cổng open
- 65505 cổng closed
- Thời gian: 10.12 giây

Scan mặc định trước đó phát hiện 23 cổng open.

Full scan phát hiện thêm 7 cổng:

| Port | Service |
|---|---|
| 3632/tcp | distccd |
| 6697/tcp | ircs-u |
| 8787/tcp | msgsrvr |
| 35127/tcp | unknown |
| 43203/tcp | unknown |
| 46635/tcp | unknown |
| 48086/tcp | unknown |

Kết quả cho thấy scan mặc định có thể bỏ sót dịch vụ chạy trên các cổng ít phổ biến.

File kết quả:

`bonus/full_65535.txt`

**Trạng thái: PASS**

---

## 12.3. Xuất đồng thời ba định dạng bằng -oA

Sử dụng `-oA` để xuất cùng một lần quét thành ba định dạng:

- `lab4_bonus.nmap`
- `lab4_bonus.xml`
- `lab4_bonus.gnmap`

Các file được lưu trong thư mục:

`bonus/`

Ý nghĩa:

- `.nmap`: thuận tiện cho người đọc.
- `.xml`: phù hợp parse/xử lý bằng công cụ.
- `.gnmap`: phù hợp grep và lọc nhanh.

Việc xuất đồng thời giúp các output có cùng nguồn dữ liệu và timestamp.

**Trạng thái: PASS**

---

## 13. Lỗi gặp phải và cách khắc phục

### 13.1. Quên mật khẩu Kali Linux

Trong quá trình thực hành không nhớ mật khẩu tài khoản Kali.

Cách khắc phục:

- Truy cập GRUB.
- Chỉnh boot entry để vào root shell.
- Đặt lại mật khẩu tài khoản Kali.
- Khởi động lại hệ thống.

Kết quả:

- Đăng nhập lại Kali thành công.
- Không ảnh hưởng các bước Nmap sau đó.

**Trạng thái: ĐÃ KHẮC PHỤC**

### 13.2. Gõ nhầm `ipconfig` trên Metasploitable 2

Ban đầu sử dụng lệnh `ipconfig` nhưng Metasploitable 2 là Linux.

Cách khắc phục:

- Sử dụng `ifconfig`.
- Xác định thành công IP `192.168.239.130`.

**Trạng thái: ĐÃ KHẮC PHỤC**

---

## 14. Kết quả tổng hợp

| Hạng mục | Kết quả | Trạng thái |
|---|---|---|
| Môi trường Host-only | Kali và Metasploitable cùng subnet | PASS |
| Ping | 0% packet loss | PASS |
| Host Discovery | 4 host up | PASS |
| TCP Connect | 23 open / 977 closed | PASS |
| SYN Scan | 23 open / 977 closed | PASS |
| FIN/Xmas/NULL | 23 open\|filtered / 977 closed | PASS |
| ACK Scan | 1000 unfiltered | PASS |
| UDP Top 20 | 2 open / 9 open\|filtered / 9 closed | PASS |
| Version Detection | Xác định nhiều service/version | PASS |
| OS Detection | Linux 2.6.X | PASS |
| Aggressive Scan | Version + OS + NSE + traceroute | PASS |
| SMB Discovery | Samba/host/domain được xác định | PASS |
| MS17-010 | Không đủ dữ liệu kết luận | KHÔNG XÁC ĐỊNH |
| Output chính | TXT + XML + grepable | PASS |
| Full 65535 ports | 30 open, thêm 7 port | PASS |
| Bonus -oA | NMAP + XML + GNMAP | PASS |

---

## 15. Cấu trúc repository

```text
LAB4/
├── README.md
├── 11_DH_TMDT-LAB4_1150070050-NguyenNgocTuVy.docx
├── ket_qua.txt
├── ket_qua.xml
├── smb.txt
│
├── images/
│   ├── 01_kali_ip.png
│   ├── 02_metasploitable_ip.png
│   ├── 03_host_discovery.png
│   ├── 04_tcp_scan.png
│   ├── 05_service_version.png
│   ├── 06_os_detection.png
│   ├── 07_nse_smb.png
│   └── 08_output_files.png
│
└── bonus/
    ├── full_65535.txt
    ├── lab4_bonus.gnmap
    ├── lab4_bonus.nmap
    ├── lab4_bonus.xml
    └── images/
        ├── 09_bonus_oA.png
        └── 10_bonus_full_65535.png
