# BÀI THỰC HÀNH: MÃ HÓA ĐỐI XỨNG CỔ ĐIỂN
**Môn học:** An toàn và bảo mật hệ thống thông tin  
**Sinh viên thực hiện:** Nguyễn Ngọc Tú Vy  
**MSSV:** 1150070050  
**Lớp:** 11_DH_TMDT  
**Giảng viên hướng dẫn:** ThS. Phạm Trọng Huynh  

---

## 1. Mục tiêu bài thực hành
- Hiện thực hoàn chỉnh 6 thuật toán mật mã đối xứng cổ điển: Caesar, Vigenère, Rail Fence, Playfair, Hoán vị cột (Transposition) và Autokey.
- Thiết kế giao diện đồ họa chuẩn hai chiều (.java và .form) tương thích hoàn toàn với Apache NetBeans GUI Builder.
- Quản lý dự án chuẩn hóa bằng Apache Maven trên nền tảng Java 17 LTS.
- Kết nối cơ sở dữ liệu Microsoft SQL Server lưu vết lịch sử giao dịch mã hóa/giải mã an toàn với PreparedStatement.
- Nghiên cứu và thực hành 3 phương pháp thám mã cổ điển: Vét cạn Caesar (Brute Force), Phân tích tần suất chữ cái (Frequency Analysis), và Tấn công bản rõ đã biết (Known Plaintext Attack).

---

## 2. Môi trường triển khai
- **Hệ điều hành:** Windows 11 Home Single Language (64-bit).
- **Java Platform:** Eclipse Adoptium Temurin OpenJDK 17.0.21+9 (HotSpot 64-Bit Server VM, Java release 17).
- **IDE:** Apache NetBeans IDE 31.
- **Build Tool:** Apache Maven 3.9.16.
- **Database:** Microsoft SQL Server 2022 Express & SQL Server Management Studio (SSMS) 22.
- **JDBC Driver:** `com.microsoft.sqlserver:mssql-jdbc:12.8.1.jre11`.

---

## 3. Danh sách 6 thuật toán & Chức năng
1. **Caesar Cipher:** Dịch vòng ký tự theo khóa $k \pmod{26}$, giữ nguyên chữ hoa/thường, khoảng trắng và số.
2. **Vigenère Cipher:** Đa bảng chữ cái với từ khóa lặp; khoảng trắng và ký tự đặc biệt không làm tiến chỉ số khóa.
3. **Rail Fence Cipher:** Xếp ký tự theo đường zíc-zắc trên $k$ hàng rào rồi đọc theo hàng; bảo toàn khoảng trắng Unicode.
4. **Playfair Cipher:** Ma trận khóa 5×5 (I/J chung ô); tách cặp 2 chữ, tự động chèn ký tự đệm X/Q, hỗ trợ tạo ma trận trực quan.
5. **Transposition Cipher:** Hoán vị cột theo mảng khóa $1..m$ (ví dụ: `2,3,1`).
6. **Autokey Cipher:** Mở rộng dòng khóa bằng chính bản rõ sau từ khóa mồi (primer key).

Mỗi form thuật toán đều trang bị đầy đủ các chức năng:
- **Encrypt / Decrypt:** Mã hóa và giải mã với cơ chế kiểm tra dữ liệu đầu vào chặt chẽ.
- **Open Ciphertext File:** Mở tệp văn bản `.txt` mã hóa UTF-8 nạp vào ô Ciphertext.
- **Save Ciphertext File:** Lưu trực tiếp bản mã ra tệp `.txt` UTF-8 trên máy tính.
- **Status:** Thanh trạng thái phản hồi kết quả thao tác trực quan.

---

## 4. Kết quả kiểm thử thực tế (100% PASS)

| STT | Thuật toán | Plaintext | Khóa (Key) | Ciphertext thực tế | Bản rõ giải mã | Đánh giá |
|:---:|---|---|---|---|---|:---:|
| 1 | Caesar | `HELLO` | `3` | `KHOOR` | `HELLO` | **PASS** |
| 2 | Vigenère | `HELLO` | `KEY` | `RIJVS` | `HELLO` | **PASS** |
| 3 | Rail Fence | `HELLO WORLD` | `3` | `HOREL OLLWD` | `HELLO WORLD` | **PASS** |
| 4 | Playfair | `HELLOWORLD` | `KEYWORD` | `GYIZSCOKCFBU` | `HELXLOWORLDX` | **PASS** |
| 5 | Transposition | `BAOMAT` | `2,3,1` | `AAOTBM` | `BAOMAT` | **PASS** |
| 6 | Autokey | `HELLO` | `KEY` | `RIJSS` | `HELLO` | **PASS** |

### Kiểm tra ngoại lệ khóa không hợp lệ:
- Caesar (`abc`): Bắt lỗi định dạng số, thông báo `"Khóa Caesar phải là số nguyên, ví dụ 3."`.
- Vigenère (`K3Y`): Bắt lỗi ký tự ngoài A-Z, thông báo `"Khóa Vigenere chỉ gồm A-Z."`.
- Rail Fence (`1`): Bắt lỗi số hàng không hợp lệ, thông báo `"Số hàng phải từ 2 đến 1000."`.
- Playfair (`KEY WORD`): Bắt lỗi khoảng trắng, thông báo `"Khóa Playfair chỉ gồm A-Z."`.
- Transposition (`1,1,2`): Bắt lỗi trùng cột, thông báo `"Khóa phải là hoán vị 1..m."`.
- Autokey (`123`): Bắt lỗi chứa chữ số, thông báo `"Khóa Autokey chỉ gồm A-Z."`.

---

## 5. Hướng dẫn mở và chạy dự án

### Cách 1: Sử dụng Apache NetBeans IDE
1. Khởi động NetBeans IDE.
2. Chọn menu `File` -> `Open Project...` (hoặc bấm `Ctrl+Shift+O`).
3. Điều hướng đến thư mục `C:\BaoMat\LAB1_MaHoaCoDien` (hoặc thư mục giải nén `LAB1_MaHoaCoDien`).
4. Bấm `Open Project`.
5. Bấm `F6` (hoặc nhấp phải chuột vào project -> `Run`) để khởi chạy `frm_Menu`.
6. Để xem/chỉnh sửa giao diện bằng NetBeans GUI Builder: Nhấp đúp vào bất kỳ tệp form nào trong package `week_01` (ví dụ `frm_Caesar.java`), sau đó nhấp vào tab **Design**.

### Cách 2: Sử dụng dòng lệnh Maven
```powershell
cd C:\BaoMat\LAB1_MaHoaCoDien
mvn clean package
mvn exec:java
```

---

## 6. Cấu trúc tệp tin nộp bài

```text
LAB10_MaHoaCoDien/
├── README.md                           # Tài liệu hướng dẫn tóm tắt
├── BaoCao_LAB10_MaHoaCoDien.docx       # Báo cáo thực hành chi tiết (Word)
├── 03_SQL_SERVER/                      # Kịch bản cơ sở dữ liệu
│   ├── 01_Tao_CSDL.sql                 # Tạo CSDL LAB1_MaHoaCoDien và bảng dbo.history
│   ├── 02_Tao_Login_MAU.sql            # Tạo login lab1_user an toàn (SQLCMD Parameter)
│   └── 03_Kiem_Tra_Du_Lieu.sql         # Truy vấn kiểm tra lịch sử thao tác
├── IMG/                                # 14 Ảnh chụp minh chứng kiểm thử thực tế
│   ├── 01_Caesar.png
│   ├── 02_Vigenere.png
│   ├── 03_RailFence.png
│   ├── 04_Playfair.png
│   ├── 05_Transposition.png
│   ├── 06_Autokey.png
│   ├── 07_TongHop.png
│   ├── 08_KetNoiSQL.png
│   ├── 09_LichSu.png
│   ├── 10_PhanTich_BruteForce.png
│   ├── 11_PhanTich_Frequency.png
│   ├── 12_PhanTich_KnownPlaintext.png
│   ├── 13_SQL_Server_SSMS.png
│   └── 14_Build_Success.png
└── LAB1_MaHoaCoDien/                   # Mã nguồn dự án Maven
    ├── pom.xml                         # Cấu hình Maven (Java 17, MSSQL JDBC)
    └── src/main/java/week_01/          # Đầy đủ 33 tệp mã nguồn .java và form .form
```

---

## 7. Vị trí Báo cáo Word
- Báo cáo định dạng Word hoàn chỉnh: `BaoCao_LAB10_MaHoaCoDien.docx` nằm ngay tại thư mục gốc của bài thực hành.
- Toàn bộ 14 hình ảnh minh chứng thực tế được nhúng trực tiếp trong báo cáo kèm chú thích và phân tích chi tiết.
