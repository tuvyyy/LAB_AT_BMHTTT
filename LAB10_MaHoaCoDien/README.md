# LAB 10 – Mã hóa đối xứng cổ điển

**Học phần:** An toàn và Bảo mật Hệ thống Thông tin  
**Sinh viên:** Nguyễn Ngọc Tú Vy – 1150070050 – 11_DH_TMDT  
**Giảng viên:** ThS. Phạm Trọng Huynh

## 1. Nội dung thực hành

Xây dựng ứng dụng Java Swing với sáu thuật toán **Caesar, Vigenère, Rail Fence, Playfair, Transposition và Autokey**. Ứng dụng gồm sáu form thuật toán, form tổng hợp, phần phân tích mật mã, giao diện kết nối SQL Server và xem lịch sử. Các form NetBeans được lưu cùng cả tệp `.java` và `.form`.

## 2. Môi trường

- Java 17, Apache NetBeans IDE, Maven.
- Java Swing / NetBeans GUI Builder.
- Microsoft SQL Server Express, SQL Server Management Studio và Microsoft JDBC Driver.

## 3. Kiểm thử

Bộ kiểm thử JUnit nằm tại `LAB1_TuLam/src/test/java/week_01/CipherTest.java`. Các bộ dữ liệu chuẩn cần đối chiếu:

| Thuật toán | Bản rõ | Khóa | Bản mã mong đợi |
|---|---|---|---|
| Caesar | `HELLO` | `3` | `KHOOR` |
| Vigenère | `HELLO` | `KEY` | `RIJVS` |
| Rail Fence | `HELLO WORLD` | `3` | `HOREL OLLWD` |
| Playfair | `HELLOWORLD` | `KEYWORD` | `GYIZSCOKCFBU` |
| Transposition | `BAOMAT` | `2,3,1` | `AAOTBM` |
| Autokey | `HELLO` | `KEY` | `RIJSS` |

Báo cáo Word trình bày kết quả, nhận xét và minh chứng chi tiết.

## 4. Tài liệu và cấu trúc thư mục

```text
LAB10_MaHoaCoDien/
├── README.md
├── TH1_LAB10_NguyenNgocTuVy_1150070050.docx
├── LAB1_TuLam/
│   ├── pom.xml
│   └── src/
│       ├── main/java/week_01/   # Mã nguồn và các tệp .form
│       └── test/java/week_01/   # JUnit
├── 03_SQL_SERVER/               # 3 tệp SQL
└── IMG/                         # 14 ảnh minh chứng
```

**[Báo cáo thực hành Word](TH1_LAB10_NguyenNgocTuVy_1150070050.docx)** trình bày chi tiết các bước thực hiện và kết quả.

## 5. Mở và chạy chương trình

1. Mở thư mục `LAB1_TuLam` bằng **File → Open Project** trong Apache NetBeans.
2. Thiết lập Java Platform của project là **JDK 17**; Maven sẽ tải các dependency trong `pom.xml`.
3. Dùng **Clean and Build** để biên dịch và chạy bộ kiểm thử, sau đó nhấn **F6** để mở `week_01.frm_Menu`.
4. Nếu cần chức năng lưu lịch sử, tạo cơ sở dữ liệu bằng các script tại `03_SQL_SERVER/`, cấu hình SQL Server và nhập thông tin kết nối trong form của ứng dụng.

Không đưa mật khẩu SQL Server, tệp `.class`, thư mục `target/` hoặc dữ liệu nhạy cảm vào repository. Hình ảnh được lưu riêng ở `IMG/`; README không nhúng ảnh kết quả.
