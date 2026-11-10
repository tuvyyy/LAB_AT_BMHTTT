# BÀI THỰC HÀNH: MÃ HÓA ĐỐI XỨNG CỔ ĐIỂN
**Môn học:** An toàn và Bảo mật Hệ thống Thông tin  
**Sinh viên thực hiện:** Nguyễn Ngọc Tú Vy  
**MSSV:** 1150070050  
**Lớp:** 11_DH_TMDT  
**Giảng viên hướng dẫn:** ThS. Phạm Trọng Huynh  

---

## 1. Thông tin Project Maven
- **Tên thư mục dự án Maven:** `LAB1_TuLam` (Đường dẫn làm việc trên máy: `C:\BaoMat\LAB1_TuLam`)
- **Group Id:** `vn.edu.lab`
- **Artifact Id:** `LAB1_TuLam`
- **Package:** `week_01`
- **Java Release:** `17` (Eclipse Adoptium Temurin JDK 17.0.21)
- **Main Class (F6):** `week_01.frm_Menu`
- **Cơ sở dữ liệu SQL Server:** `LAB1_MaHoaCoDien` (bảng `dbo.history`, tài khoản `lab1_user`)
- **JDBC Driver:** `com.microsoft.sqlserver:mssql-jdbc:12.8.1.jre11`

---

## 2. Hướng dẫn Giảng viên mở và chạy Project bằng Apache NetBeans IDE
1. Giải nén tệp nộp bài `LAB10_MaHoaCoDien_1150070050_NguyenNgocTuVy.zip` (hoặc lấy từ thư mục `LAB10_MaHoaCoDien/` trên GitHub).
2. Mở **Apache NetBeans IDE** (phiên bản 31, chạy trên JDK 17).
3. Chọn menu **File** $\rightarrow$ **Open Project...** (`Ctrl + Shift + O`), chọn thư mục **`LAB1_TuLam`** rồi nhấn **Open Project**.
4. **Kiểm tra giao diện trên GUI Builder:** Trong cửa sổ Projects, mở package `week_01`, nhấp đúp vào bất kỳ form nào (`frm_Menu`, `frm_Caesar`, `frm_Vigenere`, `frm_RailFence`, `frm_Playfair`, `frm_Transposition`, `frm_Autokey`, `frm_TongHop`, `frm_KetNoiSQL`, `frm_LichSu`, `frm_TanCong`) và chuyển sang tab **Design** để xem hoặc chỉnh sửa bố cục trực quan (đầy đủ 11 cặp `.java` và `.form` đồng bộ).
5. **Biên dịch & Chạy chương trình:**
   - Nhấn **Shift + F11** (*Clean and Build Project*) để biên dịch và chạy bộ kiểm thử tự động JUnit 5 (`CipherTest`).
   - Nhấn **F6** (*Run Project*) để khởi chạy cửa sổ điều hướng chính `week_01.frm_Menu`.
6. **Chạy bằng dòng lệnh Maven (tùy chọn):**
   ```powershell
   cd LAB1_TuLam
   mvn clean package
   ```

---

## 3. Kết quả kiểm thử thực tế (8/8 JUnit Tests PASS)

| STT | Thuật toán | Plaintext | Khóa (Key) | Ciphertext thực tế | Giải mã kiểm chứng | Kết quả |
|:---:|---|---|---|---|---|:---:|
| 1 | **Caesar** | `HELLO` | `3` | `KHOOR` | `HELLO` | **PASS** |
| 2 | **Vigenère** | `HELLO` | `KEY` | `RIJVS` | `HELLO` | **PASS** |
| 3 | **Rail Fence** | `HELLO WORLD` | `3` | `HOREL OLLWD` | `HELLO WORLD` | **PASS** |
| 4 | **Playfair** | `HELLOWORLD` | `KEYWORD` | `GYIZSCOKCFBU` | `HELXLOWORLDX` | **PASS** |
| 5 | **Transposition** | `BAOMAT` | `2,3,1` | `AAOTBM` | `BAOMAT` | **PASS** |
| 6 | **Autokey** | `HELLO` | `KEY` | `RIJSS` | `HELLO` | **PASS** |
| 7 | **Brute Force Caesar** | `KHOOR` | 26 khóa (`0..25`) | Liệt kê đủ 26 kết quả | Khóa `k = 3` ra `HELLO` | **PASS** |
| 8 | **Known Plaintext** | `HELLO` / `RIJVS` | — | Suy ra chuỗi khóa `KEYKE` | Khóa gốc lặp: `KEY` | **PASS** |

- **Kiểm tra khóa không hợp lệ:** Đã kiểm chứng các trường hợp `Caesar ("abc")`, `Vigenere ("K3Y")`, `Rail Fence ("1")`, `Playfair ("KEY WORD")`, `Transposition ("1,1,2")`, `Autokey ("123")` đều hiển thị hộp thoại báo lỗi rõ ràng, không gây dừng chương trình.
- **Đọc/ghi tệp UTF-8 (`FileTextIO`):** Hoạt động đầy đủ chức năng *Open Ciphertext File* và *Save Ciphertext File* trên tất cả các form.
- **Kết nối SQL Server (`LAB1_MaHoaCoDien`):**
  - Đã tạo CSDL `LAB1_MaHoaCoDien`, bảng `dbo.history` và login `lab1_user` trên `.\SQLEXPRESS`, kiểm thử thực tế thao tác `INSERT` và `SELECT` với tài khoản `lab1_user`.
  - Khi chưa kết nối SQL Server, `frm_TongHop` vẫn thực hiện mã hóa/giải mã bình thường và thông báo rõ trạng thái chưa lưu lịch sử.
  - Các tệp script trong `03_SQL_SERVER/` hoàn toàn không chứa mật khẩu (sử dụng biến `$(SQL_PASSWORD)` của SQLCMD).

---

## 4. Cấu trúc thư mục nộp bài

```text
LAB10_MaHoaCoDien/
├── README.md                           # Hướng dẫn mở project và tóm tắt kết quả
├── BaoCao_LAB10_MaHoaCoDien.docx       # Báo cáo thực hành chi tiết (Word, nhúng 14 ảnh)
├── 03_SQL_SERVER/                      # Kịch bản cơ sở dữ liệu SQL Server
│   ├── 01_Tao_CSDL.sql                 # Tạo CSDL LAB1_MaHoaCoDien và bảng dbo.history
│   ├── 02_Tao_Login_MAU.sql            # Tạo login lab1_user (dùng SQLCMD Parameter, không lưu mật khẩu)
│   └── 03_Kiem_Tra_Du_Lieu.sql         # Truy vấn kiểm tra dữ liệu bảng dbo.history
├── IMG/                                # 14 ảnh minh chứng thực tế (01_Caesar.png .. 14_Build_Success.png)
└── LAB1_TuLam/                         # Project Maven nộp Google Classroom
    ├── pom.xml                         # Cấu hình Maven (artifactId: LAB1_TuLam, Java 17)
    ├── src/main/java/week_01/          # 22 lớp .java và 11 tệp giao diện .form NetBeans
    └── src/test/java/week_01/          # Bộ kiểm thử tự động CipherTest.java (JUnit 5)
```
