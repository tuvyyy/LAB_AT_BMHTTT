# LAB 6 – Cấu hình chính sách truy cập trên pfSense

**Môn học:** An toàn hệ thống thông tin  
**Sinh viên:** Nguyễn Ngọc Tú Vy – 1150070050 – 11_DH_TMDT  
**Ngày thực hành:** 13/10/2026
**Youtube:**https://youtu.be/e98ghv28Mmg

## 1. Nội dung thực hành

Trong LAB 6, em dựng mô hình pfSense trên VMware Workstation, chia thành bốn mạng WAN, MGMT, LAN và DMZ. Sau khi cấu hình địa chỉ IP cho các máy, em tạo Alias, Schedule và Firewall Rules để kiểm tra chính sách truy cập website theo từng máy và theo thời gian.

Phần kiểm thử tập trung vào **5 kịch bản (KB1–KB5)**. Em sử dụng Windows Lab làm Client-A và Ubuntu Server làm web server trong DMZ. Kết quả trong README được ghi theo các ảnh đã chụp; những phép thử chưa thực hiện được ghi riêng ở cuối.

## 2. Mô hình mạng

| Thiết bị / vùng mạng | Kết nối | Địa chỉ IP |
|---|---|---|
| pfSense WAN | VMnet8 – em0, NAT | DHCP (lúc chụp: 192.168.195.131/24) |
| pfSense MGMT | VMnet4 – em1 | 192.168.56.254/24 |
| Máy quản trị | VMnet4 | 192.168.56.1/24 |
| pfSense LAN | VMnet5 – em2 | 10.6.0.1/24 |
| Windows Lab (Client-A) | VMnet5 | 10.6.0.21/24 |
| pfSense DMZ | VMnet6 – em3 | 172.16.6.1/24 |
| Ubuntu Server | VMnet6 – ens33 | 172.16.6.10/24 |
| Client-B (theo đề) | LAN | 10.6.0.22/24 – chưa có kết quả kiểm thử |
| DC01 (theo đề) | LAN | 10.6.0.10/24 – chưa kiểm thử AD/LDAP |

Trên Ubuntu, em gán thêm ba IP phụ để mô phỏng các nhóm website:

| Alias | IP | Mục đích |
|---|---|---|
| GENERAL_SITE | 172.16.6.10 | Website thông thường |
| JOB_SITE | 172.16.6.20 | Website việc làm |
| SOCIAL_SITE | 172.16.6.30 | Mạng xã hội |
| RESTRICTED_SITE | 172.16.6.40 | Website hạn chế |

Website thử nghiệm chạy bằng Python HTTP Server trên cổng 80; trang **LAB6_DMZ_WEB_OK** dùng để kiểm tra kết nối. Phần này chưa chuyển sang Nginx và IP phụ hiện được cấu hình tạm thời.

## 3. Alias và Schedule

Em tạo các IP Alias: `CLIENT_A`, `CLIENT_B`, `POLICY_CLIENTS`, `GENERAL_SITE`, `JOB_SITE`, `SOCIAL_SITE`, `RESTRICTED_SITE`. Alias `WEB_PORTS` gồm cổng **80** và **443**.

| Schedule | Thời gian | Sử dụng |
|---|---|---|
| `LUNCH_WINDOW` | Thứ Hai–Thứ Sáu, 11:30–13:00 | KB3 |
| `WORK_HOURS` | Thứ Hai–Thứ Sáu, 08:00–17:00 | KB4 |
| `TEST_NOW` | 13/10/2026, 20:00–21:00 | Kiểm thử tạm KB4 |

Sau khi kiểm thử, Schedule của rule KB4 đã được đổi lại từ `TEST_NOW` về `WORK_HOURS`.

## 4. Kết quả năm kịch bản

| KB | Chính sách | Kết quả đã kiểm tra |
|---|---|---|
| **KB1** | Chặn Client-A truy cập JOB_SITE `.20` | Client-A truy cập `.20` bị timeout; GENERAL_SITE `.10` vẫn mở được. |
| **KB2** | Chặn POLICY_CLIENTS truy cập RESTRICTED_SITE `.40` | Client-A truy cập `.40` bị timeout. Chưa thử trực tiếp trên Client-B. |
| **KB3** | Client-B chỉ vào SOCIAL_SITE `.30` trong giờ nghỉ | Đã tạo rule Pass theo `LUNCH_WINDOW` và rule Block bên dưới. Chưa kiểm thử trên Client-B. |
| **KB4** | Cho phép WEB_PORTS theo `WORK_HOURS` | Khi dùng `TEST_NOW`, Client-A mở được `.30`; ngoài giờ, lệnh `curl` tới `.30` bị timeout. KB1 vẫn chặn `.20`. Đã đổi lại `WORK_HOURS`. |
| **KB5** | Cho LAN vào web DMZ, chặn DMZ khởi tạo kết nối về LAN | Client-A mở `.10` thành công (HTTP 200). Ubuntu ping Client-A mất 100% gói; Firewall Log ghi nhận 4 gói ICMP bị chặn. |

Trong quá trình làm KB4, em từng đặt rule Block phía trên rule Pass khiến GENERAL_SITE cũng bị chặn. Sau khi kiểm tra log và sắp lại thứ tự rule, kết nối tới `.10` hoạt động bình thường.

## 5. Hình ảnh minh chứng

Ảnh đã được sắp trong thư mục `img/`, theo đúng từng nội dung để tiện xem và đối chiếu với báo cáo Word.

| Thư mục | Nội dung | Ảnh tiêu biểu |
|---|---|---|
| `img/setup/` | VMware, IP các interface, Client-A, Ubuntu Web | [Ubuntu nhận 4 IP](img/setup/06_Ubuntu_DMZ_4_IP.png) |
| `img/alias_schedule/` | Alias, WEB_PORTS, lịch làm việc và lịch thử | [Alias IP](img/alias_schedule/10_Alias_7_IP.png) |
| `img/KB1/` | Rule chặn JOB_SITE, kết quả timeout | [Kiểm thử KB1](img/KB1/15_KB1_JOB_SITE_Timeout.png) |
| `img/KB2/` | Client-A bị chặn RESTRICTED_SITE | [Kiểm thử KB2](img/KB2/16_KB2_ClientA_RESTRICTED_Timeout.png) |
| `img/KB3/` | Rule Pass/Block của SOCIAL_SITE theo giờ nghỉ | [Rule KB3](img/KB3/17_KB3_Social_Schedule_Rules.png) |
| `img/KB4/` | Truy cập trong giờ thử và timeout ngoài giờ | [Kiểm tra bằng curl](img/KB4/20_KB4_OutsideHours_Curl_Timeout.png) |
| `img/KB5/` | Rule DMZ, ping bị chặn và Firewall Log | [Firewall Log](img/KB5/24_KB5_Firewall_Log_4_ICMP_Blocks.png) |

## 6. Phần chưa kiểm tra

- Chưa có phép thử trực tiếp trên Client-B (`10.6.0.22`) ở KB2 và KB3.
- KB4 mới kiểm tra HTTP/80; chưa thử riêng HTTPS/443.
- Chưa có kết quả AD DS, LDAP, Captive Portal hoặc State Table để đưa vào báo cáo.
- Chưa kiểm thử DMZ tới DC01 (`10.6.0.10`); ảnh KB5 hiện ghi nhận chiều DMZ tới Client-A (`10.6.0.21`).
- Web server đang chạy tạm bằng Python; cấu hình Nginx và Netplan cố định chưa hoàn thành.

Các phần chưa kiểm tra được giữ riêng để không nhầm với những kết quả đã thực hiện.

## 7. Tài liệu

- Đề thực hành: `LAB6_pfSense_AD_Access_Policies_2026.pdf`.
- Báo cáo Word: `11_DH_TMDT-Lab6_1150070050_NguyenNgocTuVy.docx` (tệp nộp riêng; cần tải lên GitHub nếu muốn lưu cùng repository).
- [Tài liệu pfSense của Netgate](https://docs.netgate.com/pfsense/en/latest/).

**Lưu ý:** Không tải lên GitHub công khai file backup `config.xml` hoặc dữ liệu chứa mật khẩu chưa được xử lý.
