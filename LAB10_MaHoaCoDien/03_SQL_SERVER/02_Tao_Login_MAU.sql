-- CHỈ DÀNH CHO LAB TRÊN MÁY CÁ NHÂN. Chạy với quyền quản trị SQL Server.
-- LƯU Ý BẢO MẬT: Script sử dụng SQLCMD Parameter $(SQL_PASSWORD) để tránh lưu mật khẩu trực tiếp trong file nộp bài.
-- Khi chạy bằng sqlcmd, dùng cú pháp:
-- sqlcmd -S .\SQLEXPRESS -E -i 02_Tao_Login_MAU.sql -v SQL_PASSWORD="<MatKhauCuaBan>"
-- Khi chạy bằng SSMS, hãy bật Query -> SQLCMD Mode trước khi bấm Execute (F5),
-- hoặc thay thế chuỗi $(SQL_PASSWORD) bằng mật khẩu cá nhân của bạn (ví dụ: LabUser@2026).

:setvar SQL_PASSWORD "THAY_BANG_MAT_KHAU_CUA_BAN"

USE [master];
GO
IF NOT EXISTS (SELECT 1 FROM sys.sql_logins WHERE name = N'lab1_user')
BEGIN
    CREATE LOGIN [lab1_user]
    WITH PASSWORD = '$(SQL_PASSWORD)',
    CHECK_POLICY = ON,
    CHECK_EXPIRATION = OFF;
END
ELSE
BEGIN
    ALTER LOGIN [lab1_user] WITH PASSWORD = '$(SQL_PASSWORD)';
END;
GO
USE [LAB1_MaHoaCoDien];
GO
IF NOT EXISTS (SELECT 1 FROM sys.database_principals WHERE name = N'lab1_user')
    CREATE USER [lab1_user] FOR LOGIN [lab1_user];
GO
-- Cấp quyền vừa đủ: SELECT, INSERT, DELETE lịch sử. Không cấp db_owner.
GRANT SELECT, INSERT, DELETE ON OBJECT::dbo.history TO [lab1_user];
GO
