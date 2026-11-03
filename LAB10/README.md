# LAB10 – Remote DNS Cache Poisoning (Mô phỏng trong mạng LAB)

**Môn học:** An toàn hệ thống thông tin  
**Sinh viên:** Nguyễn Ngọc Tú Vy  
**MSSV:** 1150070050  
**Lớp:** 11_DH_TMDT  
**Ngày thực hành:** 03/11/2026  
**Trạng thái:** Đang chuẩn bị và thu thập kết quả thực nghiệm

## 1. Mục tiêu

Tìm hiểu sự khác biệt giữa local DNS spoofing và remote DNS cache poisoning thông qua mạng ảo cô lập; quan sát cơ chế TXID, UDP source port, DNS cache, kiểm tra khôi phục BIND9 và các biện pháp hardening. Các kết quả sẽ chỉ được ghi nhận sau khi có minh chứng thực tế.

## 2. Môi trường dự kiến

- VMware Workstation, pfSense-LAB6, Kali Linux và các máy Ubuntu đã dùng trong LAB trước.
- DNSLAB: `10.9.0.0/24`; DNS-SERVER dự kiến `10.9.0.10`; VICTIM `10.9.0.100`.
- ATTACKNET: `10.10.0.0/24`; pfSense `10.10.0.1`; Kali dự kiến `10.10.0.200`.
- DMZ: `172.16.6.0/24`; AUTH-DNS/APP-TEST dự kiến `172.16.6.20`; DMZ-WEB `172.16.6.10`.

> **Lưu ý:** Các IP trên là sơ đồ mục tiêu theo tài liệu giảng viên, **không phải xác nhận hệ thống đã cấu hình hoặc kiểm thử thành công**.

## 3. Cấu trúc lưu trữ

```text
LAB10/
├── README.md
├── img/          # Ảnh minh chứng thực nghiệm
├── configs/      # Cấu hình DNS, firewall (đã loại bỏ bí mật)
├── scripts/      # Script kiểm tra và tài nguyên được phép nộp
├── logs/         # Nhật ký và đầu ra kiểm tra
└── pcap/         # Gói tin ghi nhận trong môi trường LAB
```

Báo cáo Word sẽ được đưa vào thư mục `LAB10/` sau khi hoàn thiện. Các thư mục hiện có `.gitkeep` để GitHub hiển thị trước khi tải tài liệu lên.

## 4. Trạng thái thực nghiệm

| Hạng mục | Trạng thái |
|---|---|
| Đối chiếu mô hình và tài liệu | Đang thực hiện |
| Cấu hình và kiểm tra kết nối ATTACKNET | Chưa xác minh |
| DNS baseline | Chưa xác minh |
| Các thử nghiệm trong phạm vi LAB | Chưa xác minh |
| Khôi phục, hardening | Chưa xác minh |
| Ảnh, PCAP, log | Chưa tải lên |
| Báo cáo Word | Chưa tải lên |

**Quy ước:** Chỉ ghi PASS khi có kết quả kiểm tra thực tế; không dùng ảnh hoặc log giả.

## 5. Liên hệ LAB trước

Kế thừa mạng VMware và mô hình DNS mô tả tại [LAB8](../LAB8/README.md); không thay đổi nội dung các LAB trước.
