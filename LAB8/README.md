# LAB8 – Local DNS Attack

Báo cáo thực hành môn **An toàn hệ thống thông tin**.

- Họ tên: Nguyễn Ngọc Tú Vy
- MSSV: 1150070050
- Lớp: 11_DH_TMDT
- Nền tảng: VMware Workstation, pfSense CE 2.7.2, Ubuntu Server/BIND9, Kali Linux.
- Mạng thử nghiệm: DNSLAB (VMnet9), 10.9.0.0/24.

## Cấu trúc

- `README.md`: thông tin bài thực hành.
- `img/`: ảnh minh chứng đánh số từ 01 đến 15; ảnh hardening có thể có 15a–15c.
- `pcap/`: nơi bổ sung tập tin bắt gói Task 2 và Task 3 nếu cần.
- Báo cáo Word được đặt ở thư mục gốc LAB8 khi tải lên.

## Nội dung

1. Cấu hình và kiểm tra DNSLAB trên pfSense.
2. Kiểm tra BIND9 và phân giải hợp lệ.
3. Task 1: phân giải tên miền thông qua file HOSTS.
4. Task 2: phân tích phản hồi DNS giả mạo và kết quả máy Victim.
5. Task 3: phân tích lưu lượng và cache BIND9.
6. Khôi phục DNS và kiểm tra hardening.

**Lưu ý:** Tên bài và thư mục nộp là **LAB8** theo yêu cầu môn học. Domain `lab9.test` và tên một số script/tài nguyên trong thí nghiệm là tên kỹ thuật đã sử dụng, không phải tên thư mục nộp. Các con số TXID, thời gian, cache cần đối chiếu với PCAP/log gốc.
