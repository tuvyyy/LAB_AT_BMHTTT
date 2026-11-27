-- BAI 3 - TAO SQL LOGIN CHI DUOC SELECT/INSERT LICH SU
-- LUU Y BAO MAT: Khong ghi cung mat khau trong ma nguon, script nop bai hay Git.
-- Yeu cau: SQL Server phai bat SQL Server and Windows Authentication mode (Mixed Mode).
--
-- Cach chay bang sqlcmd:
--   sqlcmd -S .\SQLEXPRESS -E -i SQL\02_Tao_TaiKhoan_LAB.sql -v SQL_PASSWORD="<MatKhauThucHanh>"
--
-- Cach chay bang SSMS:
--   Bat Query -> SQLCMD Mode, bo dau '--' o dong :setvar ben duoi va thay mat khau thuc hanh truoc khi bam Execute (F5).

-- :setvar SQL_PASSWORD "THAY_BANG_MAT_KHAU_THUC_HANH"

USE master;
GO
IF SUSER_ID(N'lab3_user') IS NULL
BEGIN
    CREATE LOGIN lab3_user WITH PASSWORD = '$(SQL_PASSWORD)', CHECK_POLICY = ON;
END
ELSE
BEGIN
    ALTER LOGIN lab3_user WITH PASSWORD = '$(SQL_PASSWORD)', CHECK_POLICY = ON;
END;
GO
USE LAB3_MaHoaKhoaCongKhai;
GO
IF DATABASE_PRINCIPAL_ID(N'lab3_user') IS NULL
BEGIN
    CREATE USER lab3_user FOR LOGIN lab3_user;
END;
GO
GRANT SELECT, INSERT ON OBJECT::dbo.History TO lab3_user;
GO
