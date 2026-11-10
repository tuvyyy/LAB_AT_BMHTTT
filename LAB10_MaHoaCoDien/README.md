# LAB10 – Mã hóa đối xứng cổ điển (Java Swing / Maven / SQL Server)

**Học phần:** An toàn và bảo mật thông tin  
**Bài thực hành:** LAB 1 – Mã hóa đối xứng cổ điển (theo tài liệu LAB1_HuongDan.pdf)  
**Trạng thái:** Đang triển khai; đây là cấu trúc chuẩn bị để bổ sung mã nguồn, minh chứng và báo cáo.

## Mục tiêu

Triển khai sáu thuật toán mã hóa cổ điển bằng Java Swing: Caesar, Vigenère, Rail Fence, Playfair, Transposition và Autokey. Chương trình có các form riêng, form tổng hợp, chức năng mở/lưu văn bản, phân tích mật mã cơ bản, và lưu/xem lịch sử thao tác bằng SQL Server.

## Môi trường dự kiến

- Windows 11, Eclipse Temurin JDK 17, Apache NetBeans 31.
- Maven, Java Swing / NetBeans GUI Builder.
- Microsoft SQL Server Express, SSMS, JDBC.

## Cấu trúc thư mục

```text
LAB10_MaHoaCoDien/
├── README.md
├── LAB1_MaHoaCoDien/
│   ├── pom.xml
│   └── src/main/java/week_01/    # Java classes và NetBeans .form sẽ bổ sung
├── 03_SQL_SERVER/                 # Các script SQL không chứa mật khẩu
├── img/                           # Ảnh chụp kết quả thực tế
└── BaoCao_LAB10_MaHoaCoDien.docx  # Sẽ bổ sung khi hoàn thành
```

## Dữ liệu kiểm thử theo tài liệu

| Thuật toán | Plaintext | Khóa | Ciphertext cần đạt |
|---|---|---|---|
| Caesar | HELLO | 3 | KHOOR |
| Vigenere | HELLO | KEY | RIJVS |
| Rail Fence | HELLO WORLD | 3 | HOREL OLLWD |
| Playfair | HELLOWORLD | KEYWORD | GYIZSCOKCFBU |
| Transposition | BAOMAT | 2,3,1 | AAOTBM |
| Autokey | HELLO | KEY | RIJSS |

**Lưu ý:** Bảng trên là **kết quả yêu cầu** từ tài liệu, không phải xác nhận chương trình đã vượt qua kiểm thử.

## Tình trạng nộp bài

Thư mục này hiện là khung dự án. Mã nguồn các form, file `.form`, các script SQL, ảnh minh chứng thực tế và báo cáo Word cần được bổ sung sau khi thực hành hoàn tất. Không lưu mật khẩu SQL Server, build output hoặc dữ liệu nhạy cảm trên GitHub.

Chi tiết thao tác và kiểm thử sẽ được trình bày trong báo cáo Word khi hoàn thiện.
