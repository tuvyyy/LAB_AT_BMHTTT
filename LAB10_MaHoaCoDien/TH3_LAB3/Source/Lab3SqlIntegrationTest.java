package week_03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Kiểm thử tích hợp SQL Server (chỉ chạy khi biến môi trường LAB3_SQL_PASSWORD được cung cấp,
 * không lưu cứng mật khẩu trong mã nguồn).
 */
class Lab3SqlIntegrationTest {

    @AfterEach
    void cleanup() {
        DbConfig.clear();
    }

    @Test
    @DisplayName("SQL Server: kết nối 127.0.0.1:14333, ghi/đọc dbo.History, kiểm tra CHECK constraint và phân quyền")
    void testSqlConnectionInsertSelectAndConstraints() throws Exception {
        String pwd = System.getenv("LAB3_SQL_PASSWORD");
        Assumptions.assumeTrue(pwd != null && !pwd.isBlank(),
                "Bỏ qua test tích hợp SQL vì chưa thiết lập biến môi trường LAB3_SQL_PASSWORD.");

        String server = System.getenv().getOrDefault("LAB3_SQL_SERVER", "127.0.0.1");
        int port = Integer.parseInt(System.getenv().getOrDefault("LAB3_SQL_PORT", "14333"));
        String db = System.getenv().getOrDefault("LAB3_SQL_DB", "LAB3_MaHoaKhoaCongKhai");
        String user = System.getenv().getOrDefault("LAB3_SQL_USER", "lab3_user");

        DbConfig.set(server, port, db, user, pwd.toCharArray());
        assertTrue(DbConfig.isReady());

        String info = DbConfig.test();
        assertTrue(info.contains("Kết nối thành công"));
        assertTrue(info.contains("LAB3_MaHoaKhoaCongKhai"));

        // Ghi thành công và thất bại cho các thao tác hợp lệ
        assertEquals("(Đã lưu lịch sử SQL Server)", HistoryDao.tryInsert("RSA", "ENCRYPT", 9, true));
        assertEquals("(Đã lưu lịch sử SQL Server)", HistoryDao.tryInsert("ECDSA", "VERIFY", 12, false));

        // Đọc lại tối đa 50 dòng gần nhất
        DefaultTableModel model = HistoryDao.loadLatest(50);
        assertTrue(model.getRowCount() >= 2);
        assertEquals("ECDSA", model.getValueAt(0, 2));
        assertEquals("VERIFY", model.getValueAt(0, 3));
        assertEquals("Không", model.getValueAt(0, 5));

        assertEquals("RSA", model.getValueAt(1, 2));
        assertEquals("ENCRYPT", model.getValueAt(1, 3));
        assertEquals("Có", model.getValueAt(1, 5));

        // Ràng buộc CHECK Operation: từ chối giá trị ngoài ENCRYPT/DECRYPT/SIGN/VERIFY
        assertThrows(SQLException.class, () -> HistoryDao.insert("RSA", "DELETE", 5, true));

        // Phân quyền tối thiểu: lab3_user chỉ có SELECT & INSERT, không có DELETE
        assertThrows(SQLException.class, () -> {
            try (Connection con = DbConfig.open();
                 PreparedStatement ps = con.prepareStatement("DELETE FROM dbo.History WHERE Id = -1")) {
                ps.executeUpdate();
            }
        });
    }
}
