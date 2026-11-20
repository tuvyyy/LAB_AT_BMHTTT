# TH2 – LAB 11: Mã hóa đối xứng hiện đại

**Học phần:** An toàn và Bảo mật Hệ thống Thông tin  
**Sinh viên:** Nguyễn Ngọc Tú Vy – 1150070050  
**Lớp:** 11_DH_TMDT

## Nội dung thực hành

Bài thực hành triển khai DES, TripleDES, AES-256-GCM, RC4, Twofish và AES-CTR bằng Java Swing trên Maven, kết nối SQL Server để đăng ký, đăng nhập và ghi lịch sử thao tác. Bài nâng cao AES-256-CBC sử dụng Python với Tkinter.

## Cấu trúc bài nộp

- `LAB2_MaHoaHienDai/`: project Maven, mã nguồn Java và các form NetBeans (`.java`, `.form`).
- `SQL/`: script tạo cơ sở dữ liệu, tài khoản và truy vấn kiểm tra.
- `Python/`: giao diện AES-CBC và chế độ tự kiểm thử.
- `IMG/`: ảnh minh chứng thực hành.
- `TH2_LAB11_NguyenNgocTuVy_1150070050.docx`: báo cáo thực hành.

## Cách chạy

Mở project `LAB2_MaHoaHienDai` trong NetBeans (JDK 17 trở lên), chạy Maven Clean and Build, sau đó chạy `week_02.frm_Menu`. Cấu hình SQL Server Express theo tài liệu LAB 2, cổng 14333; thông tin đăng nhập được nhập trên giao diện. Với bài nâng cao Python, cài thư viện `cryptography` và chạy `python aes_cbc_gui.py --self-test` để kiểm thử.

> Tình trạng tải lên: README đã được tạo; các tệp mã nguồn, ảnh và Word đang cần được đưa vào thư mục này từ gói bài nộp TH2_LAB11.
