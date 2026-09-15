# LAB1 - Bắt gói tin Telnet - SSH

## Thông tin sinh viên

- **Họ và tên:** Nguyễn Ngọc Tú Vy
- **MSSV:** 1150070050
- **Lớp:** 11_DH_TMDT
- **Tên bài Lab:** Lab 1 - Bắt gói tin Telnet - SSH

---

## 1. Mục tiêu

- Thiết lập môi trường thực hành Telnet giữa Client và Server.
- Kiểm tra kết nối mạng giữa các máy ảo.
- Bắt và phân tích lưu lượng Telnet bằng Wireshark.
- Quan sát sự khác biệt về mức độ bảo mật của Telnet và SSH.
- Chứng minh dữ liệu Telnet có thể bị đọc khi lưu lượng được bắt đúng vị trí.

---

## 2. Môi trường thực hành

- **VMware Workstation**
- **Windows Lab** - Client
- **Kali Linux 2026.2** - Server
- **Wireshark**
- **Telnet Client trên Windows**
- **inetutils-telnetd trên Kali Linux**

### Địa chỉ IP

| Máy | Vai trò | Địa chỉ IP |
|---|---|---|
| Windows Lab | Client | `192.168.195.129` |
| Kali Linux | Server | `192.168.195.128` |

---

## 3. Nội dung đã thực hiện

### 3.1. Kiểm tra kết nối mạng

- Kiểm tra địa chỉ IP trên Windows bằng `ipconfig`.
- Kiểm tra địa chỉ IP trên Kali bằng `ip a`.
- Ping từ Windows sang Kali:

```text
ping 192.168.195.128
