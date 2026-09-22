# LAB 3 - Nhận diện và ứng phó các mối đe dọa đến An toàn thông tin

## Thông tin sinh viên

- Họ và tên: Nguyễn Ngọc Tú Vy
- MSSV: 1150070050
- Lớp: 11_DH_TMDT
- Môn: An toàn bảo mật hệ thống thông tin
- Lab: LAB 3 - Nhận diện và ứng phó các mối đe dọa đến An toàn thông tin
- Ngày thực hành: 22/09/2026

## Video thực hành

YouTube: **[DÁN LINK VIDEO SAU KHI UPLOAD]**

## Môi trường thực hành

- VMware Workstation Pro
- Windows Server 2025 Standard Evaluation
- Hostname: `DC01`
- Domain: `lab3.local`
- Python 3.14.7
- Wireshark 4.6.8
- Sysmon 15.22
- Autoruns 14.3
- Process Explorer 17.14
- Microsoft Defender Antivirus
- Windows Firewall

## Nội dung thực hiện

Bài thực hành triển khai các tình huống nhận diện và ứng phó mối đe dọa trong môi trường VM cô lập:

1. Xây dựng risk register và phân biệt Asset, Vulnerability, Threat, Risk và Control.
2. Kiểm thử Microsoft Defender bằng EICAR.
3. Phân tích sự kiện xác thực Windows qua Event ID 4624, 4625 và 4648.
4. Tạo và phát hiện persistence lành tính bằng Registry Run Key và Scheduled Task.
5. Theo dõi tiến trình bằng Sysmon, Autoruns và Process Explorer.
6. Tạo HTTP listener chỉ bind tại `127.0.0.1:8080`.
7. So sánh HTTP plaintext và HTTPS/TLS bằng Wireshark.
8. Phân tích DoS/DDoS qua local load test và dataset TEST-NET.
9. Phân tích mail bombing bằng dữ liệu offline.
10. Nhận diện phishing và các dạng Social Engineering.
11. Cleanup toàn bộ artefact LAB3 và kiểm tra Defender sau phục hồi.
12. Tính SHA-256 cho các file Evidence.

## Kết quả

| Hạng mục | Kết quả |
|---|---|
| Microsoft Defender phát hiện EICAR | PASS |
| Event 4625 cho `lab3user` | PASS |
| Sysmon Event ID 1 | PASS |
| Autoruns phát hiện `LAB3_Run_Demo` | PASS |
| Process Explorer xác định Python listener | PASS |
| HTTP plaintext hiển thị `TRAINING_ONLY` | PASS |
| HTTPS/TLS bảo vệ payload ứng dụng | PASS |
| Local load / DDoS / Mail log analysis | PASS |
| Phishing / Social Engineering analysis | PASS |
| Cleanup persistence, user và port 8080 | PASS |
| Defender sau cleanup | PASS |
| SHA-256 Evidence | PASS |

## Các lỗi gặp phải và cách khắc phục

### 1. Không có Internet trong VM

VM ban đầu sử dụng cấu hình mạng tĩnh/Host-only nên không có Default Gateway.

**Khắc phục:** tạm chuyển VMware Network Adapter sang NAT và đưa Ethernet về DHCP để tải công cụ. Sau khi hoàn tất, môi trường được đưa về cấu hình phù hợp với
