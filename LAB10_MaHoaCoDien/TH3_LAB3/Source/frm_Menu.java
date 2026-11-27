package week_03;

public class frm_Menu extends javax.swing.JFrame {

    static {
        UiTheme.installLookAndFeel();
    }

    public frm_Menu() {
        initComponents();
        applyTheme();
        pack();
        setLocationRelativeTo(null);
    }

    private void applyTheme() {
        UiTheme.styleFrame(this);
        UiTheme.styleTitle(jLabel1);
        UiTheme.styleSubtitle(jLabel2);
        UiTheme.styleSectionPanel(pnl_crypto);
        UiTheme.styleSectionPanel(pnl_data);
        UiTheme.styleSectionTitle(jLabel3);
        UiTheme.styleSectionTitle(jLabel4);

        UiTheme.styleSecondaryButton(btn_rsa);
        UiTheme.styleSecondaryButton(btn_ecc);
        UiTheme.styleSecondaryButton(btn_rsaaes);
        UiTheme.styleSecondaryButton(btn_signature);
        UiTheme.stylePrimaryButton(btn_sql);
        UiTheme.styleSecondaryButton(btn_history);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnl_crypto = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_rsa = new javax.swing.JButton();
        btn_ecc = new javax.swing.JButton();
        btn_rsaaes = new javax.swing.JButton();
        btn_signature = new javax.swing.JButton();
        pnl_data = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btn_sql = new javax.swing.JButton();
        btn_history = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LAB 3 - Mã hóa khóa công khai");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(36, 36, 36));
        jLabel1.setText("LAB 3 - Mã hóa khóa công khai");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Thực hành RSA, ECC, hệ mã lai RSA + AES và chữ ký số ECDSA (bài 3.1 chạy RSADemo.java).");

        pnl_crypto.setBackground(new java.awt.Color(255, 255, 255));
        pnl_crypto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 220, 226)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(36, 36, 36));
        jLabel3.setText("Chức năng mật mã");

        btn_rsa.setText("3.2 RSA");
        btn_rsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rsaActionPerformed(evt);
            }
        });

        btn_ecc.setText("3.3 ECC");
        btn_ecc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eccActionPerformed(evt);
            }
        });

        btn_rsaaes.setText("3.4 RSA + AES");
        btn_rsaaes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rsaaesActionPerformed(evt);
            }
        });

        btn_signature.setText("3.5 Chữ ký ECDSA");
        btn_signature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_signatureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_cryptoLayout = new javax.swing.GroupLayout(pnl_crypto);
        pnl_crypto.setLayout(pnl_cryptoLayout);
        pnl_cryptoLayout.setHorizontalGroup(
            pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_cryptoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(pnl_cryptoLayout.createSequentialGroup()
                        .addComponent(btn_rsa, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btn_ecc, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_cryptoLayout.createSequentialGroup()
                        .addComponent(btn_rsaaes, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btn_signature, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        pnl_cryptoLayout.setVerticalGroup(
            pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_cryptoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addGroup(pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_rsa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ecc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_rsaaes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_signature, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pnl_data.setBackground(new java.awt.Color(255, 255, 255));
        pnl_data.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 220, 226)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(36, 36, 36));
        jLabel4.setText("Dữ liệu và lịch sử");

        btn_sql.setText("Kết nối SQL Server");
        btn_sql.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sqlActionPerformed(evt);
            }
        });

        btn_history.setText("Xem lịch sử");
        btn_history.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_historyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_dataLayout = new javax.swing.GroupLayout(pnl_data);
        pnl_data.setLayout(pnl_dataLayout);
        pnl_dataLayout.setHorizontalGroup(
            pnl_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_dataLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnl_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(pnl_dataLayout.createSequentialGroup()
                        .addComponent(btn_sql, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btn_history, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        pnl_dataLayout.setVerticalGroup(
            pnl_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_dataLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addGroup(pnl_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_sql, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_history, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_crypto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_data, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(pnl_crypto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(pnl_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_rsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rsaActionPerformed
        new frm_RSA().setVisible(true);
    }//GEN-LAST:event_btn_rsaActionPerformed

    private void btn_eccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eccActionPerformed
        new frm_ECC().setVisible(true);
    }//GEN-LAST:event_btn_eccActionPerformed

    private void btn_rsaaesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rsaaesActionPerformed
        new frm_RSA_AES().setVisible(true);
    }//GEN-LAST:event_btn_rsaaesActionPerformed

    private void btn_signatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_signatureActionPerformed
        new frm_ECC_Signature().setVisible(true);
    }//GEN-LAST:event_btn_signatureActionPerformed

    private void btn_sqlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sqlActionPerformed
        new frm_KetNoiSQL().setVisible(true);
    }//GEN-LAST:event_btn_sqlActionPerformed

    private void btn_historyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_historyActionPerformed
        new frm_LichSu().setVisible(true);
    }//GEN-LAST:event_btn_historyActionPerformed

    public static void main(String args[]) {
        UiTheme.installLookAndFeel();
        java.awt.EventQueue.invokeLater(() -> {
            new frm_Menu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ecc;
    private javax.swing.JButton btn_history;
    private javax.swing.JButton btn_rsa;
    private javax.swing.JButton btn_rsaaes;
    private javax.swing.JButton btn_signature;
    private javax.swing.JButton btn_sql;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel pnl_crypto;
    private javax.swing.JPanel pnl_data;
    // End of variables declaration//GEN-END:variables
}
