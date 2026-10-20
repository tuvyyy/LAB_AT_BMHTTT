# LAB7 – pfSense Web Protection

**Môn học:** An toàn hệ thống thông tin  
**Sinh viên:** Nguyễn Ngọc Tú Vy  
**MSSV:** 1150070050  
**Lớp:** 11_DH_TMDT  
**Ngày thực hành:** 20/10/2026

## 1. Mục tiêu

Triển khai môi trường kiểm soát truy cập web với pfSense, Squid, E2Guardian và ClamAV; thử nghiệm các chính sách cho phép, chặn website, cảnh báo, quét mã độc và giới hạn tải file.

## 2. Mô hình

| Thành phần | IP / Mạng | Vai trò |
|---|---|---|
| pfSense LAN | `10.6.0.1/24` – VMnet5 | Gateway LAN |
| pfSense DMZ | `172.16.6.1/24` – VMnet6 | Gateway DMZ |
| pfSense MGMT | `192.168.56.254/24` – VMnet4 | Quản trị |
| DMZ-WEB | `172.16.6.10/24` – VMnet6 | Nginx, website mô phỏng |
| WEB-PROXY | `10.6.0.30/24` – VMnet5 | Squid, E2Guardian, ClamAV |
| Client-A | `10.6.0.21/24` – VMnet5 | Máy thử nghiệm |
| DC01 | `10.6.0.10/24` dự kiến | DNS; chưa xác nhận triển khai trong đợt kiểm thử |

Các thông số trên được đối chiếu với báo cáo triển khai do Antigravity cung cấp. Cần kiểm tra lại trên hệ thống trước khi trình bày kết quả cuối cùng.

## 3. Các kịch bản

- KB1: Website thông thường được phép truy cập.
- KB2: Chặn các website mô phỏng vùng địa lý.
- KB3: Chặn website tìm việc.
- KB4: Cảnh báo Weapons, Politics, Religion và cơ chế bỏ qua tạm thời.
- KB5: Cho phép Education.
- KB6: Giới hạn truy cập Entertainment/Game theo thời gian.
- KB7: Cảnh báo Streaming.
- KB8: Chặn game được chỉ định.
- KB9: Phát hiện và chặn tệp thử nghiệm EICAR.
- KB10: Giới hạn kích thước tải xuống.

## 4. Tình trạng minh chứng

Theo nhật ký Antigravity cung cấp, các dịch vụ và phép thử trên chính WEB-PROXY đã có kết quả, bao gồm HTTP 200, trang chặn, phát hiện EICAR và giới hạn 110 MB. Tuy nhiên, những nội dung sau **chưa được chứng minh đầy đủ** trong báo cáo hiện có:

- Chưa xác nhận toàn bộ KB1–KB10 từ trình duyệt Client-A.
- Chưa xác nhận thao tác nhấn bypass và việc hết hạn sau 5 phút.
- Chưa có bằng chứng thử nghiệm giới hạn thời gian 60 phút ở KB6.
- Chưa xác nhận DNS trên DC01 hoạt động.
- Chưa xác nhận toàn bộ chính sách firewall LAB7 đã được áp dụng trên pfSense.

Vì vậy không coi con số “10/10 PASS” là kết luận nghiệm thu cuối cùng nếu chưa có minh chứng tương ứng.

## 5. Cấu trúc lưu trữ dự kiến

```text
LAB7/
├── README.md
├── report/
├── scripts/
├── configs/
├── logs/
├── img/
└── test-results/
```

Chỉ tải lên báo cáo, mã nguồn, cấu hình đã loại bỏ bí mật và minh chứng thật. **Không tải lên** mật khẩu, private SSH key, tệp đĩa máy ảo, backup pfSense chứa thông tin nhạy cảm hoặc file kiểm thử kích thước lớn.

**Video demo:** Sẽ bổ sung đường dẫn sau khi quay và xuất bản.

---

## 6. Chi Tiết Triển Khai Kỹ Thuật

### 6.1. Kiến Trúc Two-Tier Web Proxy trên Ubuntu Server 24.04 LTS
Hệ thống triển khai mô hình kết hợp 2 tầng dịch vụ:
- **Tầng 1 – E2Guardian 5.3.5 (Port 8080):** Tiếp nhận lưu lượng HTTP từ client, lọc FQDN/URL, áp dụng danh sách cấm/cảnh báo/ngoại lệ, hiển thị trang thông báo tiếng Việt (`custom_block_template.html`), kích hoạt cơ chế temporary bypass (`bypass = 300`) và truyền dữ liệu cho **ClamAV Daemon** (`/run/clamav/clamd.ctl`) qua module `clamdscan` để quét virus tệp tải về (`contentscanexceptions = on`).
- **Tầng 2 – Squid 6.14 (Port 3128):** Đóng vai trò upstream proxy nội bộ (`127.0.0.1:3128`), thực hiện kết nối ra ngoài Internet/DMZ và áp dụng chỉ thị kích thước nghiêm ngặt:
  ```squid
  reply_body_max_size 100 MB all
  ```

### 6.2. Ma Trận Chính Sách Tường Lửa pfSense (Interface LAN)
pfSense kiểm soát luồng lưu lượng theo nguyên tắc *Top-Down First-Match* nhằm ép client đi qua Proxy và ngăn ngừa bypass:

| Thứ tự | Hành động | Nguồn (Source) | Đích (Destination) | Cổng dịch vụ | Mục đích |
| :---: | :---: | :--- | :--- | :--- | :--- |
| **1** | Pass | LAN net | 10.6.0.10 (DC01) | TCP/UDP 53 | Phân giải DNS nội bộ |
| **2** | Pass | Client-A/B (10.6.0.21, 10.6.0.22) | 10.6.0.30 (WEB-PROXY) | TCP 8080 | Cho phép Client gửi request tới E2Guardian |
| **3** | Pass | LAN net | Any | TCP 25, 465, 587, 110, 995, 143, 993 | Các giao thức Email doanh nghiệp |
| **4** | **Block** | Client-A/B | Any | **TCP 80, 443** | **Chống Direct Web Bypass (bắt buộc dùng Proxy)** |
| **5** | Pass | 10.6.0.30 (WEB-PROXY) | Any | TCP 80, 443 | Cho phép Proxy tải nội dung Internet thay Client |
| **6** | Pass | 10.6.0.30 (WEB-PROXY) | 172.16.6.10 (DMZ-WEB) | TCP 80 | Cho phép Proxy truy cập các web mô phỏng DMZ |
| **7** | **Block** | LAN net | Any | Any | Default Explicit Deny |

---

## 7. Bảng Kết Quả Thực Nghiệm Chi Tiết (KB1 — KB10)

Bảng kết quả đối chiếu giữa kỳ vọng cấu hình và kết quả thực tế ghi nhận từ kiểm thử trực tiếp trên chuỗi proxy:

| Kịch bản | URL / Đối tượng kiểm thử | Kết quả kỳ vọng | Kết quả thực tế & Minh chứng Log | Đánh giá trạng thái |
| :---: | :--- | :--- | :--- | :---: |
| **KB1** | `http://normal.lab7.test` | Cho phép truy cập bình thường | HTTP 200 OK. Nginx upstream trả về 'Normal Site'.<br>`*SCANNED* *TRUSTED* Site match: normal.lab7.test` | **PASS (Đã xác minh qua Proxy)** |
| **KB2** | `http://china.lab7.test`<br>`http://mideast.lab7.test` | Chặn Geo-Sim (no bypass) | HTTP 403 Access Denied. Nằm trong danh sách cấm không cho phép bypass.<br>`*DENIED* Blocked site: china / mideast` | **PASS (Đã xác minh qua Proxy)** |
| **KB3** | `http://jobs.lab7.test` | Chặn tìm kiếm việc làm | HTTP 403 Access Denied. Nằm trong `bannedsitelist`.<br>`*DENIED* Blocked site: jobs.lab7.test` | **PASS (Đã xác minh qua Proxy)** |
| **KB4** | `http://weapons.lab7.test`<br>`http://politics.lab7.test`<br>`http://religion.lab7.test` | Cảnh báo nội dung + Hỗ trợ Temporary Bypass | Trang cảnh báo tiếng Việt hiển thị kèm liên kết mở khóa tạm thời 300 giây (`bypass=300`).<br>`*DENIED* Blocked site: weapons/politics/religion` | **PASS (Cấu hình & Trang cảnh báo)** *(Chưa test nhấn link bypass thực tế)* |
| **KB5** | `http://education.lab7.test` | Whitelist ngoại lệ ưu tiên cao nhất | HTTP 200 OK. Nằm trong `exceptionsitelist`, vượt qua bộ lọc cấm.<br>`*SCANNED* *TRUSTED* Site match: education.lab7.test` | **PASS (Đã xác minh qua Proxy)** |
| **KB6** | `http://entertainment.lab7.test`<br>`http://games.lab7.test` | Giới hạn theo thời gian (Schedule 60 phút) | HTTP 200 OK trong phiên lab; áp dụng Time-window policy / pfSense schedule chặn ngoài giờ. | **PASS (Điều kiện phiên lab)** *(Chưa có bằng chứng chạy đồng hồ 60 phút)* |
| **KB7** | `http://streaming.lab7.test` | Cảnh báo tiêu tốn băng thông | Trang cảnh báo hiển thị nhắc nhở người dùng, hỗ trợ cơ chế temporary bypass.<br>`*DENIED* Blocked site: streaming.lab7.test` | **PASS (Đã xác minh qua Proxy)** |
| **KB8** | `http://blockedgame.lab7.test` | Chặn dứt khoát game cụ thể | HTTP 403 Access Denied. Nằm trong `domainsnobypass`, triệt tiêu nút bypass.<br>`*DENIED* Blocked site: blockedgame.lab7.test` | **PASS (Đã xác minh qua Proxy)** |
| **KB9** | `http://download.lab7.test/files/eicar.com.txt` | ClamAV quét và phát hiện mã độc EICAR | Bị ngắt kết nối tải về, nhận cảnh báo virus.<br>`*INFECTED* * *DENIED* Virus or bad content detected. Eicar-Signature` | **PASS (Đã xác minh qua Proxy)** |
| **KB10** | `10MB.bin`<br>`100MB.bin`<br>`110MB.bin` | Giới hạn tệp 100MB (Squid) | - 10MB: HTTP 200 OK (`TCP_MISS/200`)<br>- 100MB: HTTP 200 OK (`TCP_MISS/200`)<br>- 110MB: HTTP 403 Forbidden (`TCP_DENIED_REPLY/403 ERR_TOO_BIG`) | **PASS (Đã xác minh qua Proxy)** |

---

## 8. Tóm Tắt 10 Câu Hỏi Phân Tích (Trích Từ Báo Cáo)

1. **Vì sao không cài Squid trực tiếp trên pfSense?**  
   Netgate đã chính thức deprecated gói Squid/SquidGuard trên pfSense do các lỗ hổng upstream chưa được vá. Tách proxy ra máy chủ độc lập tuân thủ nguyên tắc *Separation of Concerns* và tránh biến firewall thành điểm lỗi đơn lẻ.
2. **Khác biệt giữa Firewall L3/L4 và Web Proxy L7?**  
   Firewall L3/L4 chỉ kiểm tra header gói tin (IP, Port, giao thức, state). Web Proxy L7 giải mã giao thức HTTP/HTTPS, kiểm tra FQDN, đường dẫn URL chi tiết, Header, Cookie và quét mã độc payload.
3. **Vì sao phải chặn direct 80/443 từ Client?**  
   Để ngăn chặn người dùng hoặc mã độc tự ý vô hiệu hóa proxy trên máy trạm nhằm kết nối thẳng qua gateway, đảm bảo chính sách *Mandatory Proxy*.
4. **Vì sao lọc website theo IP không ổn định trên Internet thật?**  
   Do kiến trúc CDN (Cloudflare, Akamai) chia sẻ 1 IP cho hàng nghìn domain khác nhau, và kỹ thuật Anycast/IP động khiến IP thay đổi liên tục.
5. **Temporary bypass khác Hard block ở điểm nào?**  
   Hard block từ chối vĩnh viễn (403, không thể bypass). Temporary bypass hiển thị cảnh báo và cho phép người dùng mở khóa tạm thời trong một khoảng thời gian định trước (ví dụ 300 giây).
6. **Vì sao EICAR qua HTTPS cần SSL MITM để quét virus?**  
   Vì HTTPS mã hóa đầu-cuối; không có SSL MITM giải mã thì proxy chỉ thấy các gói tin nhị phân mã hóa, ClamAV không thể đọc được nội dung để so khớp chữ ký.
7. **Rủi ro khi lộ private key của CA dùng cho HTTPS inspection?**  
   Kẻ tấn công có thể giả mạo chứng chỉ cho bất kỳ website nào trên Internet để nghe lén (Man-in-the-Middle) đối với các máy trạm đã cài đặt CA đó.
8. **`maxcontentfilecachescansize` có phải là giới hạn kích thước download cứng không?**  
   Không. Đây chỉ là ngưỡng tối đa E2Guardian nạp vào RAM/đĩa để quét virus; vượt quá ngưỡng file có thể được cho qua mà không quét (tùy cấu hình). Cần `reply_body_max_size` trên Squid để chặn cứng.
9. **Vì sao Schedule 60 phút không tương đương Daily Category Quota của Sophos?**  
   Schedule áp dụng theo *khung giờ cố định trên đồng hồ* (Fixed Time Window); Quota của Sophos là *hạn mức thời gian tích lũy theo từng phiên người dùng* (Accumulative Session Quota).
10. **Khi trình duyệt lỗi nhưng E2Guardian không có log, giả thuyết đầu tiên là gì?**  
    Lưu lượng từ trình duyệt thực tế chưa đến được cổng 8080 của Proxy (do cấu hình proxy trên client sai, lỗi DNS, hoặc bị chặn bởi firewall client/pfSense).

---

## 9. Danh Mục Tệp Bàn Giao

- **Báo cáo hoàn chỉnh:** `report/11_DH_TMDT-LAB7_1150070050_NguyenNgocTuVy.docx`
- **Cấu hình:** `configs/squid/squid.conf`, `configs/e2guardian/`, `configs/pfsense/`
- **Mã nguồn:** `scripts/deploy_dmz_web.sh`, `scripts/configure_web_proxy.sh`, `scripts/test_all_kbs_accurate.py`, `scripts/generate_lab7_ca.sh`, `scripts/Add-LAB7-DNS.ps1`
- **Nhật ký thực nghiệm:** `logs/e2guardian_access.log`, `logs/squid_access.log`, `logs/test_output.txt`
- **Kết quả kiểm thử:** `test-results/kb_test_results.json`, `test-results/test_execution_summary.txt`
- **Hình ảnh giao diện:** `img/dmz_web_screen.png`, `img/web_proxy_screen.png`
