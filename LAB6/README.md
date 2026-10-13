# LAB 6 — Xác thực Active Directory và chính sách truy cập trên pfSense

> **Bản tổng hợp đang hoàn thiện** — cập nhật theo phần thực hành đã có minh chứng ngày 13/10/2026.  
> **Phạm vi thầy yêu cầu:** thực hiện **5 kịch bản (1–5)**; **không thực hiện độc lập kịch bản 6 và 7**. Các mục LDAP, Captive Portal, Alias, Schedule và các log cần thiết vẫn thuộc mục tiêu của LAB 6.

## Thông tin sinh viên

| Mục | Nội dung |
|---|---|
| Họ và tên | Nguyễn Ngọc Tú Vy |
| MSSV | 1150070050 |
| Lớp | 11_DH_TMDT |
| Môn học | An toàn hệ thống thông tin |
| Công cụ | VMware Workstation, pfSense CE 2.7.2, Windows Lab, Ubuntu Server 24.04.5 |
| Thời gian thực hành ghi nhận | 13/10/2026 |

## 1. Mục tiêu LAB 6

- Dựng môi trường pfSense riêng với **WAN / MGMT / LAN / DMZ**.
- Tích hợp **Active Directory qua LDAP**, kiểm tra bằng **Diagnostics → Authentication**.
- Cấu hình **Captive Portal trên LAN**, xác thực bằng tài khoản domain.
- Tạo **Firewall Alias** cho máy trạm và nhóm website mô phỏng trong DMZ.
- Áp dụng **Firewall Schedule** theo giờ nghỉ / giờ hành chính.
- Thực hành **5 kịch bản** chính sách truy cập, ưu tiên kiểm chứng đầy đủ **kịch bản 5 (DMZ ↔ LAN)**.
- Dùng Firewall Log và trạng thái kết nối để chứng minh chính sách; lưu ý rule pfSense chuẩn khớp theo **IP/port/interface/schedule**, không dùng username LDAP làm Source trực tiếp.

## 2. Mô hình mạng đã triển khai

| Thành phần | VMware / interface | IP/mạng | Vai trò |
|---|---|---|---|
| pfSense WAN | VMnet8 / em0 | DHCP, 192.168.195.131/24 tại lúc chụp | WAN/NAT |
| pfSense MGMT | VMnet4 / em1 | 192.168.56.254/24 | Quản trị WebGUI |
| Máy thật | VMware VMnet4 | 192.168.56.1/24 | Máy quản trị |
| pfSense LAN | VMnet5 / em2 | 10.6.0.1/24 | Gateway LAN |
| Windows Lab — Client-A | VMnet5 | 10.6.0.21/24 | Máy thử chính sách |
| pfSense DMZ | VMnet6 / em3 | 172.16.6.1/24 | Gateway DMZ |
| Ubuntu Server — DMZ-WEB | VMnet6 / ens33 | 172.16.6.10/24 | Máy chủ web test |
| Domain Controller (theo đề) | LAN | 10.6.0.10/24 | AD DS + DNS, **chưa có minh chứng hoàn thành trong bản này** |
| Client-B (theo đề) | LAN | 10.6.0.22/24 | **Chưa có minh chứng hoàn thành trong bản này** |

**Lưu ý:** WAN dùng VMnet8 NAT hiện có nên khác subnet WAN ví dụ trong tài liệu LAB6; các subnet MGMT/LAN/DMZ bám theo đề.

## 3. Phần đã thực hiện và có minh chứng

- [x] Thiết lập VMnet4 (MGMT), VMnet5 (LAN), VMnet6 (DMZ), giữ nguyên mạng của LAB cũ.
- [x] Full Clone máy pfSense, reset cấu hình LAB cũ, gán 4 interface và địa chỉ IP LAB6.
- [x] Hoàn thành Setup Wizard, đặt hostname `pfsense-lab6`, domain `lab6.test`, múi giờ `Asia/Ho_Chi_Minh`.
- [x] Quản trị qua `https://192.168.56.254`; tạo rule chỉ cho phép máy thật `192.168.56.1` truy cập TCP/443 trên MGMT.
- [x] Thực hiện backup cấu hình ban đầu và snapshot máy ảo (được người thực hành xác nhận).
- [x] Cấu hình Client-A `10.6.0.21/24`, ping `10.6.0.1` thành công **4/4 gói, 0% mất gói**.
- [x] Cấu hình Ubuntu ens33 `172.16.6.10/24`, default gateway `172.16.6.1`.
- [x] Kịch bản 5: tạo rule **Block DMZ subnets → LAN subnets** trên DMZ và **Pass Client-A → 172.16.6.10 TCP/80** trên LAN.
- [x] Kịch bản 5, chiều **LAN → DMZ**: Client-A mở `http://172.16.6.10`; Python HTTP Server trên Ubuntu ghi nhận **GET / HTTP/1.1 — 200**.

> **Giới hạn chứng cứ:** HTTP server hiện dùng `python3 -m http.server 80` để thử nhanh; **Nginx chưa được xác nhận cài**. Ảnh trình duyệt đầu tiên là directory listing, sau đó đang chuẩn bị trang test riêng. Không ghi nhận đã hoàn thành Nginx hoặc toàn bộ kịch bản 5 khi chưa đủ phép thử.

## 4. Tình trạng 5 kịch bản

| Kịch bản | Nội dung | Trạng thái xác minh trong nhánh thực hành này |
|---|---|---|
| **1** | Client-A bị chặn JOB_SITE (172.16.6.20) | Chưa có ảnh rule + kết quả |
| **2** | Chặn RESTRICTED_SITE (172.16.6.40) cho A và B | Chưa có ảnh rule + kết quả |
| **3** | Client-B chỉ được SOCIAL_SITE (172.16.6.30) trong giờ nghỉ | Chưa có ảnh rule + kết quả |
| **4** | Giới hạn HTTP/HTTPS theo WORK_HOURS | Chưa có ảnh rule + kết quả |
| **5** | Cô lập DMZ khỏi LAN, LAN truy cập web cần thiết | **Đã tạo 2 rule; xác nhận chiều LAN → DMZ; chờ kết quả DMZ → LAN và Firewall Log** |

**Không ghi nhận 6–7 thành các kịch bản độc lập** theo phạm vi được giao; vẫn dùng Log/State khi cần chứng minh policy.

## 5. Việc cần bổ sung trước khi nộp bản hoàn chỉnh

- [ ] Ảnh thử **DMZ → LAN** bị Block và bản ghi **Firewall Log** trùng source/destination của kịch bản 5.
- [ ] Hoàn thiện web test DMZ (ưu tiên Nginx) và các IP `.20`, `.30`, `.40`.
- [ ] Bằng chứng và phân tích kết quả kịch bản 1–4.
- [ ] AD DS + DNS, user `sv_a`, `sv_b`, service account LDAP; Diagnostics → Authentication.
- [ ] Captive Portal, Alias, Schedule; các ảnh test cần thiết.
- [ ] Bảng kết quả, 12 câu hỏi phân tích theo đề và ảnh minh chứng.
- [ ] Xuất `config.xml` cuối cùng; **không tải lên bản config chứa thông tin nhạy cảm ở repo công khai nếu chưa xử lý an toàn**.
- [ ] Tải báo cáo Word hoàn chỉnh vào thư mục này khi đã sẵn sàng.

## 6. Tệp báo cáo

Tên báo cáo đang biên soạn: **`11_DH_TMDT-Lab6_1150070050_NguyenNgocTuVy.docx`**.  
Báo cáo này tổng hợp phần đã thực hành với ảnh chụp thật. **Tệp Word sẽ được bổ sung riêng vào thư mục này** khi tải lên GitHub.

## 7. Tài liệu tham khảo

- Tài liệu thực hành **LAB6_pfSense_AD_Access_Policies_2026.pdf** do giảng viên cung cấp.
- [Netgate pfSense Documentation](https://docs.netgate.com/pfsense/en/latest/)
- [Ubuntu Server Documentation](https://documentation.ubuntu.com/server/)

---

**Trạng thái:** *Bản nháp có minh chứng, chưa phải bản nộp hoàn chỉnh*. README sẽ được cập nhật cùng Word, ảnh và kết quả khi hoàn tất các kịch bản.
