# LAB1 - Bắt gói tin Telnet - SSH

## Thông tin sinh viên

- **Họ và tên:** Nguyễn Ngọc Tú Vy
- **MSSV:** 1150070050
- **Lớp:** 11_DH_TMDT
- **Tên bài Lab:** Lab 1 - Bắt gói tin Telnet - SSH

---

## Nội dung đã thực hiện

### 1. Chuẩn bị môi trường

Bài thực hành được triển khai trên VMware Workstation với:

- **Windows Lab** làm Client.
- **Kali Linux** làm Server.
- **Wireshark** dùng để bắt và phân tích gói tin.

Địa chỉ IP sử dụng:

- Windows Lab: `192.168.195.129`
- Kali Linux: `192.168.195.128`

Kiểm tra kết nối từ Windows tới Kali bằng lệnh:

`ping 192.168.195.128`

Kết quả: nhận đủ `4/4` gói tin, `0% packet loss`.

---

### 2. Thực hành Telnet

Trên Kali Linux đã cấu hình Telnet Server và kiểm tra TCP port 23 bằng:

`ss -ltn | grep ':23'`

Kết quả cho thấy Telnet Server đang lắng nghe tại **TCP/23**.

Trên Windows Lab đã bật Telnet Client và thực hiện kết nối:

`telnet 192.168.195.128 23`

Sau khi đăng nhập vào Kali qua Telnet, đã thực hiện các lệnh:

- `whoami`
- `pwd`
- `mkdir test_telnet`
- `ls`
- `echo hello_telnet`
- `who`

Wireshark được chạy trên Kali Linux với Display Filter:

`tcp.port == 23`

Sau đó sử dụng **Follow TCP Stream** để phân tích phiên Telnet.

### Kết quả Telnet

Wireshark có thể đọc được nhiều nội dung của phiên Telnet, bao gồm:

- Tài khoản `kali`
- Chuỗi `hello_telnet`
- Thư mục `test_telnet`
- Địa chỉ Client `192.168.195.129`
- Một số lệnh và kết quả trả về

Kết quả cho thấy **Telnet không mã hóa nội dung phiên làm việc**. Khi lưu lượng bị bắt đúng vị trí, dữ liệu trao đổi có thể được quan sát dưới dạng có thể đọc được.

---

### 3. Thực hành SSH

Trên Kali Linux đã bật OpenSSH Server bằng:

`sudo systemctl enable --now ssh`

Kiểm tra trạng thái:

`sudo systemctl status ssh`

Kết quả:

- SSH Server ở trạng thái **active (running)**.
- Server lắng nghe tại **TCP port 22**.

Từ Windows Lab thực hiện kết nối:

`ssh kali@192.168.195.128`

Ở lần kết nối đầu tiên, SSH hiển thị ED25519 host-key fingerprint:

`SHA256:hrQHC6YpP+RDutcB6gxY8m39q/NqNnJQ2Vccrt9qRD8`

Sau khi xác nhận fingerprint, Windows lưu thông tin Server vào danh sách `known_hosts`.

Trong phiên SSH đã thực hiện:

- `whoami`
- `pwd`
- `echo hello_ssh`

---

### 4. Bắt gói SSH bằng Wireshark

Wireshark được sử dụng với Display Filter:

`tcp.port == 22`

Wireshark ghi nhận lưu lượng SSH giữa:

`192.168.195.129 <-> 192.168.195.128`

Trong cột Info xuất hiện các gói:

- `Client: Encrypted packet`
- `Server: Encrypted packet`

Khi sử dụng **Follow TCP Stream**, nội dung phiên xuất hiện dưới dạng dữ liệu đã mã hóa và không thể đọc trực tiếp các lệnh như:

- `whoami`
- `pwd`
- `hello_ssh`

### Kết quả SSH

SSH vẫn để lộ một số metadata mạng như:

- IP nguồn và IP đích
- TCP port 22
- thời điểm truyền
- kích thước gói tin
- hướng truyền

Tuy nhiên, nội dung phiên làm việc không thể đọc trực tiếp như Telnet vì payload đã được mã hóa.

---

### 5. SSH Public-Key Authentication

Trên Windows Lab đã tạo cặp khóa ED25519 bằng:

`ssh-keygen`

Các file được tạo:

- Private Key: `C:\Users\Tu Vy\.ssh\id_ed25519`
- Public Key: `C:\Users\Tu Vy\.ssh\id_ed25519.pub`

Public Key được chuyển sang Kali bằng lệnh:

`scp "%USERPROFILE%\.ssh\id_ed25519.pub" kali@192.168.195.128:/tmp/id_ed25519.pub`

Trên Kali Linux đã tạo thư mục SSH và cấu hình `authorized_keys`:

- Tạo `/home/kali/.ssh`
- Thiết lập quyền thư mục là `700`
- Đưa Public Key vào `/home/kali/.ssh/authorized_keys`
- Thiết lập quyền file `authorized_keys` là `600`

Sau đó từ Windows thực hiện lại:

`ssh kali@192.168.195.128`

### Kết quả Public-Key Authentication

Windows Lab đăng nhập vào Kali Linux **mà không yêu cầu nhập mật khẩu**.

Điều này xác nhận **SSH Public-Key Authentication đã được cấu hình và hoạt động thành công**.

---

## Kết quả thực hiện

Qua bài thực hành đã đạt được các kết quả:

- Thiết lập kết nối mạng thành công giữa Windows Lab và Kali Linux.
- Kết nối Telnet thành công qua TCP/23.
- Bắt và phân tích được lưu lượng Telnet bằng Wireshark.
- Quan sát được nội dung plaintext của phiên Telnet.
- Cấu hình SSH Server thành công trên Kali Linux.
- Kết nối SSH thành công từ Windows tới Kali qua TCP/22.
- Quan sát được các gói SSH đã mã hóa bằng Wireshark.
- Follow TCP Stream của SSH không hiển thị nội dung lệnh dưới dạng plaintext.
- Quan sát và xác nhận SSH host-key fingerprint.
- Tạo cặp khóa ED25519.
- Cấu hình và kiểm tra thành công SSH Public-Key Authentication.

---

## Nhận xét

Telnet và SSH đều có thể được sử dụng để truy cập hệ thống từ xa nhưng có sự khác biệt lớn về bảo mật.

**Telnet** không mã hóa dữ liệu truyền trên kênh. Vì vậy, khi lưu lượng Telnet bị bắt, nội dung phiên có thể bị đọc. Việc sử dụng mật khẩu dài hoặc phức tạp không giải quyết được điểm yếu này vì bản thân kênh truyền vẫn không được mã hóa.

**SSH** mã hóa payload của phiên làm việc. Wireshark vẫn có thể quan sát metadata mạng nhưng không thể đọc trực tiếp nội dung các lệnh và dữ liệu trao đổi như với Telnet.

SSH còn hỗ trợ **Host Key** và **Public-Key Authentication**, giúp tăng mức độ an toàn trong quá trình xác thực và quản trị hệ thống từ xa.

---

## Lưu ý

- Bài thực hành được thực hiện trên môi trường máy ảo cá nhân nên giao diện và phiên bản phần mềm có thể khác hình minh họa trong tài liệu hướng dẫn.
- Wireshark được chạy trực tiếp trên Kali Linux để bảo đảm quan sát được lưu lượng đi vào và đi ra của Server.
- Repository phải được duy trì ở chế độ **Public** để giảng viên có thể truy cập và kiểm tra bài làm.
- Toàn bộ file của Lab 1 được lưu đúng trong thư mục `LAB1`.

---

## File báo cáo

`Lab1.2_11_DH_TMDT_1150070050_NguyenNgocTuVy.docx`

Video quá trình thực hiện Lab được upload lên YouTube và đường dẫn video được chèn vào đầu file báo cáo Word.

---

## Cấu trúc Repository

LAB_AT_BMHTTT/  
└── LAB1/  
&nbsp;&nbsp;&nbsp;&nbsp;├── README.md  
&nbsp;&nbsp;&nbsp;&nbsp;└── Lab1.2_11_DH_TMDT_1150070050_NguyenNgocTuVy.docx
