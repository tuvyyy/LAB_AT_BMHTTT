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
