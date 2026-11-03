# LAB10 – REMOTE DNS CACHE POISONING

**Môn học:** An toàn bảo mật hệ thống thông tin  
**Sinh viên:** Nguyễn Ngọc Tú Vy  
**MSSV:** 1150070050  
**Lớp:** 11_DH_TMDT  
**Giảng viên hướng dẫn:** ThS. Phạm Trọng Huynh

## Giới thiệu

LAB10 tìm hiểu cơ chế đầu độc bộ nhớ đệm DNS từ xa (Remote DNS Cache Poisoning), trên cơ sở môi trường DNS đã triển khai ở LAB8. Trọng tâm của bài là sự khác biệt giữa tấn công trong cùng mạng nội bộ và tình huống máy kiểm thử nằm ở một phân đoạn mạng riêng, không thể trực tiếp quan sát truy vấn DNS của resolver.

## Môi trường thực hành

Bài thực hành sử dụng VMware Workstation, pfSense, Kali Linux và các máy Ubuntu phục vụ phân giải DNS, kiểm thử phía máy khách và dịch vụ web mô phỏng. pfSense phân tách các vùng mạng chính:

- **DNSLAB – 10.9.0.0/24:** DNS-SERVER và VICTIM.
- **ATTACKNET – 10.10.0.0/24:** Kali Linux.
- **DMZ – 172.16.6.0/24:** Các dịch vụ DNS và web phục vụ kịch bản.

## Nội dung thực hiện

1. Kiểm tra cấu hình mạng, phân vùng ATTACKNET và khả năng kết nối giữa các máy ảo.
2. Đối chiếu kết quả phân giải DNS và truy cập web ở trạng thái ban đầu.
3. Quan sát kịch bản mô phỏng Kaminsky, sự thay đổi trong kết quả phân giải và các dấu hiệu liên quan đến DNS cache.
4. Khôi phục BIND9, rà soát chính sách truy vấn đệ quy và các cơ chế bảo vệ DNS.

Các nhận xét, kết quả quan sát và giới hạn kiểm chứng được trình bày cụ thể trong báo cáo thực hành.

## Tài liệu nộp

| Thành phần | Nội dung |
|---|---|
| [Báo cáo LAB10](11_DH_TMDT-Lab10_1150070050_NguyenNgocTuVy.docx) | Mô hình mạng, các bước thực hành, kết quả, phân tích và câu hỏi cuối bài |
| `img/` | 13 hình minh chứng và sơ đồ sử dụng trong báo cáo |

README chỉ giới thiệu khái quát bài thực hành; phần trình bày chi tiết và đối chiếu minh chứng nằm trong **báo cáo Word**.
