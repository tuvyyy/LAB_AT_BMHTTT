# LAB8 – LOCAL DNS ATTACK

**Môn học:** An toàn hệ thống thông tin  
**Sinh viên:** Nguyễn Ngọc Tú Vy  
**MSSV:** 1150070050  
**Lớp:** 11_DH_TMDT  
**Ngày thực hành:** 28/10/2026  
**Nền tảng:** VMware Workstation, pfSense CE 2.7.2, Ubuntu Server, Kali Linux

---

## 1. Giới thiệu và mục tiêu

Bài thực hành tìm hiểu cách hệ thống phân giải tên miền hoạt động và những rủi ro khi câu trả lời DNS bị thay đổi trên máy trạm hoặc máy chủ phân giải. Các thử nghiệm được bố trí trong mạng ảo dành riêng cho LAB để quan sát kết quả trước, trong và sau khi thay đổi dữ liệu phân giải.

Nội dung chính:

- Cấu hình mạng DNSLAB, các địa chỉ IP và chính sách tường lửa trên pfSense.
- Cấu hình BIND9; kiểm tra bản ghi DNS, phân giải thuận/nghịch và truy cập Web hợp lệ.
- **Task 1 – HOSTS file:** khảo sát tác động của `/etc/hosts` đến phân giải tên miền trên máy VICTIM.
- **Task 2 – Direct DNS Spoofing:** quan sát phản hồi DNS được máy VICTIM tiếp nhận và các gói tin liên quan trong Wireshark.
- **Task 3 – DNS Cache Poisoning:** kiểm tra bản ghi trong bộ nhớ đệm của BIND9 và phản hồi từ resolver.
- Khôi phục dữ liệu DNS hợp lệ và kiểm tra một số biện pháp bảo vệ sau thực hành.

Thư mục nộp bài có tên **LAB8** theo yêu cầu môn học. Tên miền thử nghiệm `lab9.test` và `external.lab9.test` được giữ theo cấu hình đã sử dụng trong môi trường thực hành.

## 2. Môi trường và mô hình mạng

| Thành phần | Địa chỉ IP / Mạng | Vai trò |
|---|---|---|
| pfSense WAN | `192.168.195.131/24` – VMnet8 | Kết nối NAT của môi trường VMware |
| pfSense MGMT | `192.168.56.254/24` – VMnet4 | Giao diện quản trị |
| pfSense LAN-DOMAIN | `10.6.0.1/24` – VMnet5 | Mạng nội bộ từ các LAB trước |
| pfSense DMZ | `172.16.6.1/24` – VMnet6 | Gateway cho mạng máy chủ |
| pfSense DNSLAB | `10.9.0.1/24` – VMnet9 | Interface của mạng thử nghiệm DNS |
| DNS-SERVER | `10.9.0.10/24` | Ubuntu Server, dịch vụ BIND9 |
| VICTIM | `10.9.0.100/24` | Máy kiểm thử phân giải DNS |
| Kali Linux | `10.9.0.200/24` | Máy thực hiện các tình huống kiểm thử |
| DMZ-WEB | `172.16.6.10` | Website hợp lệ trong DMZ |
| APP-TEST / AUTH-DNS | `172.16.6.20` | Máy chủ DNS có thẩm quyền mô phỏng |

### Sơ đồ logic

```text
                        VMware Workstation
                   +-------------------------+
                   |    pfSense CE 2.7.2     |
                   | WAN / MGMT / LAN / DMZ  |
                   | DNSLAB: 10.9.0.1        |
                   +------------+------------+
                                |
                       VMnet9 - DNSLAB
                           10.9.0.0/24
                                |
           +--------------------+-------------------+
           |                    |                   |
      DNS-SERVER              VICTIM               Kali
      10.9.0.10             10.9.0.100           10.9.0.200
       BIND9                 DNS Client          Máy kiểm thử

           pfSense DMZ: 172.16.6.1/24 (VMnet6)
                         |
              +----------+----------+
              |                     |
           DMZ-WEB               APP-TEST
         172.16.6.10           172.16.6.20
```

**Lưu ý về phạm vi mạng:** Các ảnh cấu hình DNS-SERVER và VICTIM còn thể hiện đường mặc định qua `192.168.195.2` (adapter NAT). Vì vậy, VMnet9 là phân đoạn dành cho thử nghiệm, nhưng không nên hiểu rằng toàn bộ kết nối mạng của hai máy ảo đã được cô lập tuyệt đối khỏi NAT.

## 3. Cấu hình ban đầu

### 3.1. pfSense và chính sách tường lửa

pfSense nhận interface `em4` với địa chỉ `10.9.0.1/24` để phục vụ mạng DNSLAB. Các interface WAN, MGMT, LAN-DOMAIN và DMZ từ những LAB trước được giữ lại.

Ảnh kiểm tra bộ lọc `pfctl -sr` cho thấy các quy tắc chính trên DNSLAB:

- Cho phép DNS-SERVER (`10.9.0.10`) truy vấn DNS đến APP-TEST (`172.16.6.20`) qua TCP/UDP 53.
- Cho phép lưu lượng HTTP đã chỉ định từ DNSLAB đến DMZ-WEB (`172.16.6.10:80`).
- Có các quy tắc chặn truy cập đến LAN-DOMAIN (`10.6.0.0/24`) và MGMT (`192.168.56.0/24`).
- Áp dụng quy tắc chặn mặc định đối với lưu lượng khác trên interface thử nghiệm.

### 3.2. Địa chỉ IP và dịch vụ

DNS-SERVER sử dụng `10.9.0.10/24` trên `ens32`, dịch vụ `named.service` đang chạy. VICTIM có địa chỉ `10.9.0.100/24` và `/etc/resolv.conf` trỏ về `10.9.0.10`. Kali có địa chỉ `10.9.0.200/24` trên `eth1`; kiểm tra ICMP tới DNS-SERVER và VICTIM đều nhận 3/3 gói.

### 3.3. Kiểm tra BIND9

Các lệnh `named-checkconf` và `named-checkzone` được sử dụng để kiểm tra cấu hình, zone thuận `lab9.test` và zone nghịch `0.9.10.in-addr.arpa`.

Kết quả trên ảnh cho thấy các zone nạp được và trả về `OK`. Khi kiểm tra zone thuận có cảnh báo liên quan đến tên máy chủ NS bên ngoài zone chưa có bản ghi địa chỉ; cần lưu ý cảnh báo này khi xem xét cấu hình delegation.

## 4. Nội dung và kết quả thực hành

### 4.1. Baseline DNS trước khi thử nghiệm

Trước khi thay đổi phân giải tên miền, từ VICTIM thực hiện các phép kiểm tra:

- `dig @10.9.0.10 www.lab9.test` trả bản ghi A `172.16.6.10`.
- `curl -I http://172.16.6.10/` trả `HTTP/1.0 200 OK`.

Các kết quả này là mốc để đối chiếu với những bước sau.

### 4.2. Task 1 – HOSTS file

Trên VICTIM, thêm bản ghi `10.9.0.200 www.lab9.test` vào `/etc/hosts`. Sau khi thay đổi:

- `getent hosts www.lab9.test` nhận `10.9.0.200`.
- `curl http://www.lab9.test/` trả nội dung trang Web mô phỏng trên Kali.
- `dig @10.9.0.10 www.lab9.test` vẫn nhận địa chỉ gốc `172.16.6.10` từ BIND9.

Sự khác biệt xuất hiện vì `getent` sử dụng cơ chế phân giải tên miền của hệ điều hành, có thể ưu tiên `/etc/hosts`; trong khi `dig` gửi truy vấn trực tiếp đến DNS server được chỉ định. Sau thử nghiệm, bản ghi thêm vào HOSTS được xóa và `getent` trở lại địa chỉ hợp lệ.

### 4.3. Task 2 – Direct DNS Spoofing

Ảnh Wireshark ghi nhận truy vấn từ VICTIM (`10.9.0.100`) đến DNS-SERVER (`10.9.0.10`), cùng phản hồi DNS mang Transaction ID `0x7c14`. Trong một lượt kiểm tra trên VICTIM được chụp riêng, truy vấn tên miền `www.external.lab9.test` nhận A record `10.9.0.200` với TTL `120` giây và Query time `80 ms`. Lệnh `curl` tới máy Kali hiển thị nội dung trang Web giả lập.

Hai ảnh minh chứng không nhất thiết thuộc cùng một lượt truy vấn; vì vậy chỉ số TXID và cổng UDP cần đối chiếu với PCAP gốc khi phân tích chi tiết.

### 4.4. Task 3 – DNS Cache Poisoning

Task 3 tập trung quan sát quá trình phân giải qua DNS-SERVER và nội dung cache BIND9. Ảnh Wireshark cho thấy có trao đổi DNS giữa DNS-SERVER (`10.9.0.10`) và APP-TEST (`172.16.6.20`).

Trong ảnh kiểm tra cache sau thử nghiệm:

- `rndc dumpdb -cache` xuất dữ liệu bộ nhớ đệm.
- Bản ghi `www.external.lab9.test` trong file dump trỏ về `10.9.0.200`, TTL còn `117`.
- Truy vấn tiếp theo tới `10.9.0.10` vẫn trả `10.9.0.200`, TTL giảm còn `113`.

Các ảnh này cho thấy resolver đang lưu và trả về bản ghi thay đổi trong khoảng thời gian kiểm tra. Việc xác định chính xác gói phản hồi nào làm cache thay đổi cần dựa vào PCAP Task 3.

### 4.5. Khôi phục phân giải DNS

Sau khi kết thúc phần thử nghiệm, hệ thống được kiểm tra lại bằng `dig`, `getent` và `curl`. Ảnh kết quả cho thấy:

- `www.external.lab9.test` trở về `172.16.6.10`.
- `www.lab9.test` trỏ về `172.16.6.10`.
- `getent hosts www.lab9.test` nhận địa chỉ hợp lệ.
- HTTP tới `172.16.6.10` trả `200 OK`.

### 4.6. Kiểm tra hardening

Các kiểm tra cuối bài tập trung vào ba thành phần:

1. **Kali:** chạy `ip link set eth1 promisc off`; `ip -d link show eth1` hiển thị `promiscuity 0`.
2. **pfSense:** kiểm tra lại bộ luật lọc trên `em4`, bao gồm các quy tắc chặn LAN-DOMAIN, MGMT và lưu lượng mặc định.
3. **BIND9:** `named-checkconf` trả kết quả hợp lệ; ảnh truy vấn `dig` hiển thị `COOKIE: ... (good)` và file cấu hình không còn tùy chọn `send-cookie no;`.

Trạng thái `COOKIE: ... (good)` xác nhận việc xử lý COOKIE trong truy vấn đã kiểm tra; riêng ảnh này chưa đủ chứng minh chính sách bắt buộc xác thực DNS COOKIE đối với mọi upstream server.

## 5. Tổng hợp kết quả

| Hạng mục | Kết quả quan sát | Minh chứng |
|---|---|---|
| Kết nối VMnet9 | Kali ping DNS-SERVER và VICTIM thành công | 01–05 |
| BIND9 | Dịch vụ chạy, kiểm tra cú pháp/zone có kết quả OK kèm cảnh báo zone thuận | 03, 06 |
| Baseline | A record 172.16.6.10, HTTP 200 | 07 |
| Task 1 | HOSTS chuyển hướng đến 10.9.0.200, truy vấn DNS trực tiếp vẫn trả IP gốc | 08–09 |
| Task 2 | VICTIM nhận A record 10.9.0.200, Wireshark có lưu lượng DNS liên quan | 10–11 |
| Task 3 | BIND9 cache chứa 10.9.0.200 và tiếp tục trả bản ghi này | 12–13 |
| Restoration | DNS trả lại 172.16.6.10, HTTP 200 | 14 |
| Hardening | Promiscuity 0; kiểm tra firewall; DNS COOKIE xuất hiện trong truy vấn | 15, 15a–15c |

**Giới hạn minh chứng:** Bản ghi PCAP gốc của Task 2/3 chưa được đưa vào thư mục này khi soạn README. Các kết luận chi tiết về tính khớp TXID, port và thứ tự gói tin chỉ nên đưa ra sau khi mở và đối chiếu hai file PCAP. Đồng thời, các adapter NAT còn tồn tại trên một số VM nên cần phân biệt DNSLAB với phạm vi cô lập mạng của toàn hệ điều hành.

## 6. Cấu trúc thư mục nộp bài

```text
LAB8/
├── README.md
├── LAB8_NguyenNgocTuVy_11TMDT_1150070050.docx
├── img/
│   ├── 01_pfsense_dns_interface.png
│   ├── 02_pfsense_dns_rules.png
│   ├── 03_dns_server_ip.png
│   ├── 04_victim_ip.png
│   ├── 05_kali_ip.png
│   ├── 06_bind_validation.png
│   ├── 07_baseline_dns.png
│   ├── 08_task1_redirect.png
│   ├── 09_task1_original_dns.png
│   ├── 10_task2_wireshark.png
│   ├── 11_task2_victim_result.png
│   ├── 12_task3_wireshark.png
│   ├── 13_task3_bind_cache.png
│   ├── 14_restored_dns.png
│   ├── 15_hardening_verified.png
│   ├── 15a_hardening_kali_promisc.png   (ảnh phụ)
│   ├── 15b_hardening_pfsense_rules.png  (ảnh phụ)
│   └── 15c_hardening_bind_cookie.png   (ảnh phụ)
└── pcap/
    ├── task2_evidence.pcap             (bổ sung khi có file gốc)
    └── task3_evidence.pcap             (bổ sung khi có file gốc)
```

Các file được ghi chú “bổ sung” chỉ là vị trí dự kiến, không phải xác nhận đã tải lên repository. Ảnh sẽ hiển thị trong README sau khi upload đúng tên vào thư mục `img/`.

## 7. Nhận xét

Qua bài thực hành có thể thấy kết quả phân giải tên miền phụ thuộc vào vị trí dữ liệu bị thay đổi. Đối với `/etc/hosts`, tác động chủ yếu ở máy trạm sử dụng tệp này. Khi phản hồi DNS bị thay đổi trong quá trình truy vấn, máy khách có thể nhận địa chỉ không mong muốn. Nếu bản ghi sai được lưu trong cache resolver, nhiều truy vấn tiếp theo qua cùng resolver có thể chịu ảnh hưởng đến khi cache được cập nhật hoặc làm sạch.

Việc lưu lại kết quả từ `dig`, `curl`, BIND9 và Wireshark giúp phân biệt thay đổi ở máy trạm với thay đổi tại máy chủ phân giải. Bên cạnh đó, việc kiểm tra luật pfSense, trạng thái card mạng và cấu hình DNS sau thử nghiệm là cần thiết để đưa môi trường về trạng thái kiểm thử bình thường.

---

**Tài liệu:** Đề thực hành Local DNS Attack; ảnh chụp và kết quả kiểm thử từ môi trường VMware Workstation.