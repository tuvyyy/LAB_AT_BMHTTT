# TH3 - LAB 3: Mã hóa khóa công khai

Bài thực hành thuộc học phần An toàn và Bảo mật Hệ thống Thông tin. Chương trình được xây dựng bằng Java Swing, sử dụng các thuật toán mật mã khóa công khai và SQL Server để lưu lịch sử thao tác.

## Nội dung thực hành

- Bài 3.1: Mã hóa và giải mã RSA-2048, RSA-3072 trên Console.
- Bài 3.2: Mã hóa và giải mã RSA-OAEP trên giao diện Swing.
- Bài 3.3: Mã hóa ECC trên đường cong secp256r1, lưu và nạp khóa, kiểm tra trường hợp sai khóa.
- Bài 3.4: Hệ mã lai RSA + AES-256-GCM, xử lý văn bản dài và phát hiện bản mã bị sửa đổi.
- Bài 3.5: Tạo và xác minh chữ ký số ECDSA.
- Kết nối SQL Server, ghi nhận và tra cứu lịch sử thao tác.

## Môi trường thực hiện

Java 17, Apache Maven, Apache NetBeans, Java Swing, SQL Server Express và JDBC.

## Cấu trúc thư mục

- [Source](Source/LAB3_MaHoaKhoaCongKhai/): Mã nguồn Java, giao diện Swing và kiểm thử JUnit.
- [SQL](SQL/): Script tạo cơ sở dữ liệu, phân quyền tài khoản và truy vấn lịch sử.
- [Báo cáo thực hành](BaoCao_TH3_LAB3.docx): Nội dung thực hiện, kết quả kiểm thử và hình ảnh minh chứng.

## Hướng dẫn chạy

1. Thực thi hai script SQL `01_Tao_CSDL.sql` và `02_Tao_TaiKhoan_LAB.sql` theo thứ tự. Thiết lập mật khẩu thực hành thông qua biến SQL_PASSWORD.
2. Mở project Maven tại `Source/LAB3_MaHoaKhoaCongKhai` bằng NetBeans hoặc mở terminal tại thư mục này.
3. Chạy `mvn clean verify` để kiểm thử và `mvn exec:java` để mở chương trình.
4. Nhập thông tin kết nối SQL Server trên form kết nối. Để chạy kiểm thử tích hợp SQL, thiết lập biến môi trường LAB3_SQL_PASSWORD trước khi chạy Maven.
5. Sử dụng `03_KiemTra_LichSu.sql` để đối chiếu dữ liệu lịch sử.

## Kết quả

Các chức năng RSA, ECC, RSA + AES, ECDSA và lưu lịch sử SQL Server đã được kiểm thử trong môi trường thực hành. Chi tiết các tình huống kiểm thử và hình ảnh kết quả được trình bày trong báo cáo Word.
