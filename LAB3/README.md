# LAB3 - Nhận diện và ứng phó các mối đe dọa đến an toàn thông tin

## Thông tin sinh viên

- **Họ và tên:** Nguyễn Ngọc Tú Vy
- **MSSV:** 1150070050
- **Lớp:** 11_DH_TMDT
- **Tên bài Lab:** Lab 3 - Nhận diện và ứng phó các mối đe dọa đến an toàn thông tin

---

## Môi trường thực hành

Bài thực hành được thực hiện trên môi trường VMware Workstation.

Các thành phần chính:

- Windows 11 25H2 x64
- Microsoft Defender Antivirus
- Windows PowerShell 5.1
- Python 3.14.7
- Wireshark 4.6.8 + Npcap
- Sysmon 15.22
- Autoruns 14.3
- Process Explorer 17.14

Máy ảo được cấu hình mạng Host-only khi thực hiện bài Lab.
Trong quá trình tải công cụ, máy ảo được chuyển tạm sang NAT và sau đó chuyển lại Host-only.

---

## Nội dung đã thực hiện

### 1. Chuẩn bị môi trường

Đã thực hiện:

- Tạo cấu trúc thư mục `C:\LAB3`.
- Tạo các thư mục Evidence, Tools, Downloads và Assets.
- Cài đặt Python 3.14.7.
- Cài đặt Wireshark 4.6.8 và Npcap.
- Tải Sysmon 15.22.
- Tải Autoruns 14.3.
- Tải Process Explorer 17.14.
- Kiểm tra phiên bản các công cụ.
- Bật Microsoft Defender Real-time Protection.
- Bật Tamper Protection.
- Kiểm tra Windows Firewall.

---

### 2. Baseline hệ thống

Đã thu thập trạng thái ban đầu của máy trước khi tạo các tình huống thử nghiệm:

- Thông tin hệ điều hành.
- Trạng thái Microsoft Defender.
- Trạng thái Windows Firewall.
- Cấu hình mạng.
- Danh sách tiến trình.

Các kết quả được lưu trong thư mục `C:\LAB3\Evidence`.

---

### 3. Tình huống 1 - Asset, Vulnerability, Threat và Risk

Thực hiện xây dựng Risk Register để phân biệt:

- Asset
- Vulnerability
- Threat
- Risk
- Control

Đồng thời phân loại các nguồn đe dọa thành:

- Hành động vô ý.
- Hành động cố ý.
- Thảm họa tự nhiên.
- Lỗi kỹ thuật.
- Lỗi quản lý.

---

### 4. Tình huống 2 - Malware / EICAR

Sử dụng EICAR Standard Anti-Virus Test File để kiểm tra khả năng phát hiện và cách ly của Microsoft Defender.

Không tắt Microsoft Defender và không tạo exclusion trong quá trình thực hiện.

---

### 5. Tình huống 3 - Password Attack và Keylogging

Tạo tài khoản thử nghiệm `lab3user` và quan sát các Event ID:

- 4624
- 4625
- 4648

Thực hiện kiểm tra đăng nhập đúng/sai và thay đổi mật khẩu để quan sát sự thay đổi trong Security Log.

---

### 6. Tình huống 4 - Persistence và Backdoor

Sử dụng các artefact thử nghiệm an toàn có tiền tố `LAB3_`.

Công cụ sử dụng:

- Sysmon
- Autoruns
- Process Explorer

Thực hiện kiểm tra:

- Run Registry
- Scheduled Task
- Process
- Listener cục bộ `127.0.0.1:8080`

---

### 7. Tình huống 5 - Sniffing, MITM và Spoofing

Sử dụng Wireshark để so sánh:

- HTTP plaintext trên `127.0.0.1:8080`
- HTTPS/TLS trên TCP/443

Không thực hiện ARP poisoning, DNS spoofing, session hijacking hoặc MITM chủ động.

---

### 8. Tình huống 6 - DoS, DDoS và Mail Bombing

DoS được mô phỏng bằng tải cục bộ có giới hạn trên:

`127.0.0.1:8080`

DDoS và Mail Bombing chỉ được phân tích bằng dataset offline được cung cấp trong bài Lab.

Không tạo DDoS hoặc gửi email hàng loạt thực tế.

---

### 9. Tình huống 7 - Social Engineering và Phishing

Phân tích các mẫu offline:

- Phishing
- Spear Phishing
- Watering Hole
- Pretexting
- Baiting
- Quid Pro Quo

Không truy cập các domain trong mẫu huấn luyện.

---

## Cleanup và Recovery

Sau khi thu thập đủ bằng chứng, thực hiện:

- Xóa persistence LAB3.
- Dừng HTTP listener trên port 8080.
- Xóa tài khoản `lab3user`.
- Kiểm tra lại Microsoft Defender.
- So sánh Autoruns trước và sau cleanup.
- Tính SHA-256 cho các file Evidence.

---

## Kết quả

Các bước thực hành được thực hiện trong môi trường máy ảo phục vụ đào tạo.

Microsoft Defender, Windows Firewall và các công cụ giám sát được giữ hoạt động trong suốt quá trình thực hiện.

Các artefact thử nghiệm chỉ được tạo trong phạm vi máy Lab và được cleanup sau khi hoàn thành.

---

## File báo cáo

`11_DH_TMDT-LAB3_1150070050-NguyenNgocTuVy.docx`

Video quá trình thực hiện Lab được upload lên YouTube và đường dẫn video được chèn vào đầu file báo cáo Word.

---

## Evidence

Các output và log phục vụ bài thực hành được lưu trong thư mục:

`LAB3/Evidence/`

File kiểm tra tính toàn vẹn:

`evidence_sha256.csv`

---

## Lưu ý

- Repository được duy trì ở chế độ Public.
- Không upload mật khẩu, token, cookie hoặc dữ liệu cá nhân.
- Không upload installer hoặc executable của Python, Wireshark hoặc Sysinternals.
- Không upload file bị Microsoft Defender quarantine.
- Không thực hiện DoS/DDoS, spoofing hoặc MITM đối với hệ thống bên ngoài môi trường Lab.

---

## Cấu trúc Repository

```text
LAB_AT_BMHTTT/
├── LAB1/
│   ├── README.md
│   └── Lab1.2_11_DH_TMDT_1150070050_NguyenNgocTuVy.docx
│
└── LAB3/
    ├── README.md
    ├── 11_DH_TMDT-LAB3_1150070050-NguyenNgocTuVy.docx
    ├── Evidence/
    └── evidence_sha256.csv
