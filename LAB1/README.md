AB1 - Bắt gói tin Telnet - SSH

Họ và tên: Nguyễn Ngọc Tú Vy

MSSV: 1150070050

Lớp: 11_DH_TMDT

Tên bài Lab: Lab 1 - Bắt gói tin Telnet - SSH

Nội dung đã thực hiện

Dựng môi trường thực hành trên VMware Workstation.

Sử dụng Windows Lab làm Client và Kali Linux làm Server.

Kiểm tra địa chỉ IP và kết nối mạng giữa hai máy.

Cài đặt và bật Telnet Server trên Kali (TCP/23).

Bật Telnet Client trên Windows.

Kết nối Telnet từ Windows tới Kali.

Thực hiện các lệnh whoami, pwd, mkdir test_telnet, ls, echo hello_telnet, who.

Bắt lưu lượng Telnet bằng Wireshark trên Kali với filter tcp.port == 23.

Dùng Follow TCP Stream để quan sát nội dung phiên Telnet ở dạng có thể đọc được.

Thử thay đổi mật khẩu tài khoản sang mật khẩu phức tạp hơn.

Kết quả thực hiện

Windows Lab: 192.168.195.129

Kali Linux: 192.168.195.128

Ping giữa Client và Server thành công, 0% packet loss.

Telnet Server lắng nghe tại TCP/23.

Kết nối Telnet thành công bằng tài khoản kali.

Wireshark bắt được traffic Telnet giữa Client và Server.

Follow TCP Stream cho thấy các chuỗi như hello_telnet, test_telnet, tài khoản kali và địa chỉ Client 192.168.195.129.

Kết quả chứng minh Telnet không mã hóa nội dung phiên.

Lưu ý

Phần thử lại Telnet sau khi đổi mật khẩu phức tạp không hoàn tất ổn định, nên không ghi nhận bằng chứng lần hai.

Phần SSH chưa có ảnh thực nghiệm trong phiên làm hiện tại; không sử dụng ảnh do AI tạo hoặc ảnh của người khác.

Repository phải luôn để Public trước khi nộp.

File nộp

Báo cáo: Lab1.2_11_DH_TMDT_1150070050_NguyenNgocTuVy_FINAL.docx

Video: upload YouTube và dán link vào đầu báo cáo.

Google Classroom: nộp URL repository LAB_AT_BMHTTT.
