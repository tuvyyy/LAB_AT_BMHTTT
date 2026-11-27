package week_03;

public class frm_LichSu extends javax.swing.JFrame {

    static {
        UiTheme.installLookAndFeel();
    }

    public frm_LichSu() {
        initComponents();
        applyTheme();
        pack();
        setLocationRelativeTo(null);
        tbl_history.setModel(HistoryDao.emptyModel());
        UiTheme.applyHistoryTableColumns(tbl_history);
        loadData();
    }

    private void applyTheme() {
        UiTheme.styleFrame(this);
        UiTheme.styleTitle(jLabel1);
        UiTheme.styleSubtitle(lbl_subtitle);
        UiTheme.styleTable(tbl_history, jScrollPane1);
        UiTheme.stylePrimaryButton(btn_refresh);
        UiTheme.setStatusNormal(lbl_status, "Đang tải...");
    }

    /** Đọc 50 dòng mới nhất từ SQL Server và hiện lên JTable. */
    private void loadData() {
        try {
            tbl_history.setModel(HistoryDao.loadLatest(50));
            UiTheme.applyHistoryTableColumns(tbl_history);
            UiTheme.setStatusNormal(lbl_status,
                    "Đã tải " + tbl_history.getRowCount() + " bản ghi mới nhất từ bảng dbo.History.");
        } catch (java.sql.SQLException ex) {
            UiTheme.setStatusError(lbl_status, "Không đọc được lịch sử: " + ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbl_subtitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_history = new javax.swing.JTable();
        btn_refresh = new javax.swing.JButton();
        lbl_status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lịch sử thao tác (SQL Server)");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(36, 36, 36));
        jLabel1.setText("Lịch sử thao tác");

        lbl_subtitle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbl_subtitle.setForeground(new java.awt.Color(102, 102, 102));
        lbl_subtitle.setText("50 bản ghi gần nhất từ bảng dbo.History (LAB3_MaHoaKhoaCongKhai).");

        jScrollPane1.setViewportView(tbl_history);

        btn_refresh.setText("Tải lại");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        lbl_status.setText("Đang tải...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_subtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(lbl_status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(lbl_subtitle)
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_status, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        loadData();
    }//GEN-LAST:event_btn_refreshActionPerformed

    public static void main(String args[]) {
        UiTheme.installLookAndFeel();
        java.awt.EventQueue.invokeLater(() -> {
            new frm_LichSu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_refresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JLabel lbl_subtitle;
    private javax.swing.JTable tbl_history;
    // End of variables declaration//GEN-END:variables
}
