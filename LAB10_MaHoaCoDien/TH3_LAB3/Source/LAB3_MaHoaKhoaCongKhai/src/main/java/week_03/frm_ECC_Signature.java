package week_03;

import javax.swing.JOptionPane;

public class frm_ECC_Signature extends javax.swing.JFrame {

    static {
        UiTheme.installLookAndFeel();
    }

    private final ECCSignature ecdsa = new ECCSignature();

    public frm_ECC_Signature() {
        initComponents();
        applyTheme();
        pack();
        setLocationRelativeTo(null);
    }

    private void applyTheme() {
        UiTheme.styleFrame(this);
        UiTheme.styleTitle(jLabel1);
        UiTheme.styleSubtitle(lbl_subtitle);
        UiTheme.styleSectionPanel(pnl_keys);
        UiTheme.styleSectionPanel(pnl_sign);
        UiTheme.styleStatusPanel(pnl_status);
        UiTheme.styleSectionTitle(lbl_sec_keys);
        UiTheme.styleSectionTitle(lbl_sec_sign);

        UiTheme.styleFieldLabel(jLabel2);
        UiTheme.styleFieldLabel(jLabel3);
        UiTheme.styleFieldLabel(jLabel4);
        UiTheme.styleFieldLabel(jLabel5);

        UiTheme.styleTextArea(txt_public, jScrollPane1, true, false);
        UiTheme.styleTextArea(txt_private, jScrollPane2, true, true);
        UiTheme.styleTextArea(txt_message, jScrollPane3, false, true);
        UiTheme.styleTextArea(txt_signature, jScrollPane4, false, false);

        UiTheme.styleSecondaryButton(btn_generate);
        UiTheme.styleSecondaryButton(btn_savekeys);
        UiTheme.styleSecondaryButton(btn_loadkeys);
        UiTheme.stylePrimaryButton(btn_sign);
        UiTheme.styleSecondaryButton(btn_verify);
        UiTheme.setStatusNormal(lbl_status, "Bấm Generate Keys hoặc Load Keys.");
    }

    /** Hiện khóa công khai (Base64) và trạng thái khóa riêng thử nghiệm dạng ẩn lên form. */
    private void showKeys() {
        txt_public.setText(ecdsa.getPublicKeyBase64());
        txt_private.setText(CryptoSupport.maskPrivateKey(ecdsa.getPrivateKeyBase64()));
        txt_public.setCaretPosition(0);
        txt_private.setCaretPosition(0);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbl_subtitle = new javax.swing.JLabel();
        pnl_keys = new javax.swing.JPanel();
        lbl_sec_keys = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_public = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_private = new javax.swing.JTextArea();
        btn_generate = new javax.swing.JButton();
        btn_savekeys = new javax.swing.JButton();
        btn_loadkeys = new javax.swing.JButton();
        pnl_sign = new javax.swing.JPanel();
        lbl_sec_sign = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_message = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_signature = new javax.swing.JTextArea();
        btn_sign = new javax.swing.JButton();
        btn_verify = new javax.swing.JButton();
        lbl_result = new javax.swing.JLabel();
        pnl_status = new javax.swing.JPanel();
        lbl_status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ECDSA Signature");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(36, 36, 36));
        jLabel1.setText("Chữ ký số ECDSA (SHA256withECDSA)");

        lbl_subtitle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbl_subtitle.setForeground(new java.awt.Color(102, 102, 102));
        lbl_subtitle.setText("Ký bằng khóa riêng và xác minh bằng khóa công khai trên đường cong secp256r1 (không mã hóa nội dung).");

        pnl_keys.setBackground(new java.awt.Color(255, 255, 255));
        pnl_keys.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 220, 226)));

        lbl_sec_keys.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbl_sec_keys.setForeground(new java.awt.Color(36, 36, 36));
        lbl_sec_keys.setText("Quản lý khóa");

        jLabel2.setText("Public key:");

        txt_public.setEditable(false);
        txt_public.setColumns(20);
        txt_public.setLineWrap(true);
        txt_public.setRows(2);
        jScrollPane1.setViewportView(txt_public);

        jLabel3.setText("Private key:");

        txt_private.setEditable(false);
        txt_private.setColumns(20);
        txt_private.setLineWrap(true);
        txt_private.setRows(1);
        jScrollPane2.setViewportView(txt_private);

        btn_generate.setText("Generate Keys");
        btn_generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generateActionPerformed(evt);
            }
        });

        btn_savekeys.setText("Save Keys");
        btn_savekeys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_savekeysActionPerformed(evt);
            }
        });

        btn_loadkeys.setText("Load Keys");
        btn_loadkeys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loadkeysActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_keysLayout = new javax.swing.GroupLayout(pnl_keys);
        pnl_keys.setLayout(pnl_keysLayout);
        pnl_keysLayout.setHorizontalGroup(
            pnl_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_keysLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnl_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_sec_keys)
                    .addGroup(pnl_keysLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
                    .addGroup(pnl_keysLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(pnl_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_keysLayout.createSequentialGroup()
                                .addComponent(btn_generate, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btn_savekeys, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btn_loadkeys, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))))
                .addGap(16, 16, 16))
        );
        pnl_keysLayout.setVerticalGroup(
            pnl_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_keysLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbl_sec_keys)
                .addGap(10, 10, 10)
                .addGroup(pnl_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnl_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnl_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_generate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_savekeys, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_loadkeys, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        pnl_sign.setBackground(new java.awt.Color(255, 255, 255));
        pnl_sign.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 220, 226)));

        lbl_sec_sign.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbl_sec_sign.setForeground(new java.awt.Color(36, 36, 36));
        lbl_sec_sign.setText("Ký và xác minh");

        jLabel4.setText("Message:");

        txt_message.setColumns(20);
        txt_message.setLineWrap(true);
        txt_message.setRows(2);
        jScrollPane3.setViewportView(txt_message);

        jLabel5.setText("Signature:");

        txt_signature.setColumns(20);
        txt_signature.setLineWrap(true);
        txt_signature.setRows(2);
        jScrollPane4.setViewportView(txt_signature);

        btn_sign.setText("Sign");
        btn_sign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_signActionPerformed(evt);
            }
        });

        btn_verify.setText("Verify");
        btn_verify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verifyActionPerformed(evt);
            }
        });

        lbl_result.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout pnl_signLayout = new javax.swing.GroupLayout(pnl_sign);
        pnl_sign.setLayout(pnl_signLayout);
        pnl_signLayout.setHorizontalGroup(
            pnl_signLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_signLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnl_signLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_sec_sign)
                    .addGroup(pnl_signLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3))
                    .addGroup(pnl_signLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(pnl_signLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_signLayout.createSequentialGroup()
                                .addComponent(btn_sign, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btn_verify, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(lbl_result, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4))))
                .addGap(16, 16, 16))
        );
        pnl_signLayout.setVerticalGroup(
            pnl_signLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_signLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbl_sec_sign)
                .addGap(10, 10, 10)
                .addGroup(pnl_signLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnl_signLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(pnl_signLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_sign, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_verify, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_result, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pnl_status.setBackground(new java.awt.Color(255, 255, 255));
        pnl_status.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 220, 226)));

        lbl_status.setText("Bấm Generate Keys hoặc Load Keys.");

        javax.swing.GroupLayout pnl_statusLayout = new javax.swing.GroupLayout(pnl_status);
        pnl_status.setLayout(pnl_statusLayout);
        pnl_statusLayout.setHorizontalGroup(
            pnl_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_statusLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbl_status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        pnl_statusLayout.setVerticalGroup(
            pnl_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_statusLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl_status, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_subtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_keys, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_sign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(lbl_subtitle)
                .addGap(16, 16, 16)
                .addComponent(pnl_keys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(pnl_sign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(pnl_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generateActionPerformed
        try {
            ecdsa.generateKeys();
            showKeys();
            lbl_result.setText("");
            UiTheme.setStatusNormal(lbl_status,
                    "Đã tạo cặp khóa mới. Chữ ký cũ sẽ không hợp lệ với khóa mới.");
        } catch (Exception ex) {
            UiTheme.setStatusError(lbl_status, "Không tạo được khóa: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Không tạo được khóa: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_generateActionPerformed

    private void btn_savekeysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_savekeysActionPerformed
        if (!ecdsa.hasPublicKey()) {
            JOptionPane.showMessageDialog(this, "Chưa có khóa để lưu.");
            return;
        }
        String content = CryptoSupport.toKeyFile(ecdsa.getPublicKeyBase64(), ecdsa.getPrivateKeyBase64());
        String path = FileTextIO.saveString(this, content, "ecdsa_keys.txt");
        if (path != null) {
            UiTheme.setStatusNormal(lbl_status,
                    "Đã lưu tệp khóa thử nghiệm (giữ bí mật dòng PRIVATE, không đưa lên Git).");
        }
    }//GEN-LAST:event_btn_savekeysActionPerformed

    private void btn_loadkeysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadkeysActionPerformed
        String content = FileTextIO.openString(this);
        if (content == null) {
            return;
        }
        try {
            String[] keys = CryptoSupport.fromKeyFile(content);
            ecdsa.loadKeys(keys[0], keys[1]);
            showKeys();
            lbl_result.setText("");
            UiTheme.setStatusNormal(lbl_status, "Đã nạp khóa từ tệp.");
        } catch (Exception ex) {
            UiTheme.setStatusError(lbl_status, "Tệp khóa không hợp lệ: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Tệp khóa không hợp lệ: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_loadkeysActionPerformed

    private void btn_signActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_signActionPerformed
        String message = txt_message.getText();
        try {
            txt_signature.setText(ecdsa.sign(message));
            txt_signature.setCaretPosition(0);
            lbl_result.setText("");
            UiTheme.setStatusSuccess(lbl_status,
                    "Đã ký bằng khóa riêng. "
                    + HistoryDao.tryInsert("ECDSA", "SIGN", message.length(), true));
        } catch (Exception ex) {
            String logStatus = HistoryDao.tryInsert("ECDSA", "SIGN", message.length(), false);
            UiTheme.setStatusError(lbl_status, "Ký số ECDSA thất bại. " + logStatus);
            JOptionPane.showMessageDialog(this, "Ký thất bại: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_signActionPerformed

    private void btn_verifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verifyActionPerformed
        String message = txt_message.getText();
        try {
            boolean ok = ecdsa.verify(message, txt_signature.getText());
            lbl_result.setText(ok ? "VALID" : "INVALID");
            lbl_result.setForeground(ok ? UiTheme.SUCCESS : UiTheme.ERROR);
            String msg = "Đã xác minh bằng khóa công khai. "
                    + HistoryDao.tryInsert("ECDSA", "VERIFY", message.length(), ok);
            if (ok) {
                UiTheme.setStatusSuccess(lbl_status, msg);
            } else {
                UiTheme.setStatusError(lbl_status, msg);
            }
        } catch (Exception ex) {
            String logStatus = HistoryDao.tryInsert("ECDSA", "VERIFY", message.length(), false);
            lbl_result.setText("INVALID");
            lbl_result.setForeground(UiTheme.ERROR);
            UiTheme.setStatusError(lbl_status, "Xác minh ECDSA thất bại. " + logStatus);
            JOptionPane.showMessageDialog(this, "Xác minh thất bại: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_verifyActionPerformed

    public static void main(String args[]) {
        UiTheme.installLookAndFeel();
        java.awt.EventQueue.invokeLater(() -> {
            new frm_ECC_Signature().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_generate;
    private javax.swing.JButton btn_loadkeys;
    private javax.swing.JButton btn_savekeys;
    private javax.swing.JButton btn_sign;
    private javax.swing.JButton btn_verify;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl_result;
    private javax.swing.JLabel lbl_sec_keys;
    private javax.swing.JLabel lbl_sec_sign;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JLabel lbl_subtitle;
    private javax.swing.JPanel pnl_keys;
    private javax.swing.JPanel pnl_sign;
    private javax.swing.JPanel pnl_status;
    private javax.swing.JTextArea txt_message;
    private javax.swing.JTextArea txt_private;
    private javax.swing.JTextArea txt_public;
    private javax.swing.JTextArea txt_signature;
    // End of variables declaration//GEN-END:variables
}
