# LAB3_MaHoaKhoaCongKhai

Project Java Maven cho **LAB 3 – Mã hóa khóa công khai** (Học phần An toàn và Bảo mật Hệ thống Thông tin).

## Thông số project
- **Group ID:** `vn.edu.lab`
- **Artifact ID:** `LAB3_MaHoaKhoaCongKhai`
- **Package:** `week_03`
- **Java version:** 17 (`UTF-8`)
- **Main class:** `week_03.frm_Menu`
- **Dependencies:** `com.microsoft.sqlserver:mssql-jdbc:12.8.1.jre11`, `org.junit.jupiter:junit-jupiter:5.10.3` (test)

## Thành phần mã nguồn (`src/main/java/week_03`)
- **Lớp xử lý, giao diện dùng chung và tiện ích:** `CryptoSupport`, `FileTextIO`, `DbConfig`, `HistoryDao`, `UiTheme`, `RSACipher`, `RSADemo`, `ECCCipher`, `RSA_AES_Cipher`, `ECCSignature`.
- **7 giao diện Swing (kèm `.form` chuẩn NetBeans GUI Builder):** `frm_Menu`, `frm_RSA`, `frm_ECC`, `frm_RSA_AES`, `frm_ECC_Signature`, `frm_KetNoiSQL`, `frm_LichSu`.
- **Kiểm thử JUnit 5 (`src/test/java/week_03`):** `Lab3CryptoTest`, `Lab3SqlIntegrationTest`.

## Build và chạy nhanh
```powershell
mvn clean verify
mvn exec:java
```
Chạy kèm test tích hợp SQL Server:
```powershell
$env:LAB3_SQL_PASSWORD = "<MatKhauThucHanh>"
mvn clean verify
```
Chạy riêng Bài 3.1 Console:
```powershell
java -Dfile.encoding=UTF-8 -cp target\classes week_03.RSADemo
```
Mật khẩu SQL Server không lưu cứng trong mã nguồn; nhập trực tiếp trên form `frm_KetNoiSQL` khi sử dụng.
