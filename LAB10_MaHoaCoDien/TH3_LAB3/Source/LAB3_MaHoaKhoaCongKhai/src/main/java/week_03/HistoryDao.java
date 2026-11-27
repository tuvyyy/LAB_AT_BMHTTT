package week_03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;

/**
 * Ghi / đọc bảng dbo.History (LAB3_MaHoaKhoaCongKhai).
 * Chỉ lưu độ dài dữ liệu và kết quả, không lưu khóa hay bản rõ.
 */
public final class HistoryDao {

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private HistoryDao() {
    }

    /** operation: ENCRYPT, DECRYPT, SIGN hoặc VERIFY (ràng buộc CHECK trong SQL). */
    public static void insert(String algorithm, String operation, int inputLength, boolean success)
            throws SQLException {
        String sql = "INSERT INTO dbo.History(Algorithm, Operation, InputLength, Success) VALUES (?, ?, ?, ?)";
        try (Connection con = DbConfig.open(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, algorithm);
            ps.setString(2, operation);
            ps.setInt(3, Math.max(0, inputLength));
            ps.setBoolean(4, success);
            ps.executeUpdate();
        }
    }

    public static String tryInsert(String algorithm, String operation, int inputLength, boolean success) {
        if (!DbConfig.isReady()) {
            return "(Chưa kết nối SQL Server - chưa lưu lịch sử)";
        }
        try {
            insert(algorithm, operation, inputLength, success);
            return "(Đã lưu lịch sử SQL Server)";
        } catch (SQLException ex) {
            return "(Lỗi lưu lịch sử: " + ex.getMessage() + ")";
        }
    }

    public static DefaultTableModel emptyModel() {
        return new DefaultTableModel(
                new Object[] {"Id", "Thời gian", "Thuật toán", "Thao tác", "Độ dài vào", "Thành công"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public static DefaultTableModel loadLatest(int top) throws SQLException {
        DefaultTableModel model = emptyModel();
        String sql = "SELECT TOP (?) Id, CreatedAt, Algorithm, Operation, InputLength, Success "
                + "FROM dbo.History ORDER BY Id DESC";
        try (Connection con = DbConfig.open(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, top);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Timestamp ts = rs.getTimestamp(2);
                    String formattedTime = ts == null ? "" : ts.toLocalDateTime().format(TIME_FMT);
                    model.addRow(new Object[] {
                        rs.getLong(1),
                        formattedTime,
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getBoolean(6) ? "Có" : "Không"
                    });
                }
            }
        }
        return model;
    }
}
