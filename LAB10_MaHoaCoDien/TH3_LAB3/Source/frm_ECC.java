package week_03;

import javax.swing.JOptionPane;

public class frm_ECC extends javax.swing.JFrame {

    static {
        UiTheme.installLookAndFeel();
    }

    private final ECCCipher ecc = new ECCCipher();

    public frm_ECC() {
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
        UiTheme.styleSectionPanel(pnl_crypto);
        UiTheme.styleStatusPanel(pnl_status);
        UiTheme.styleSectionTitle(lbl_sec_keys);
        UiTheme.styleSectionTitle(lbl_sec_crypto);

        UiTheme.styleFieldLabel(jLabel2);
        UiTheme.styleFieldLabel(jLabel3);
        UiTheme.styleFieldLabel(jLabel4);
        UiTheme.styleFieldLabel(jLabel5);

        UiTheme.styleTextArea(txt_public, jScrollPane1, true, false);
        UiTheme.styleTextArea(txt_private, jScrollPane2, true, true);
        UiTheme.styleTextArea(txt_plaintext, jScrollPane3, false, true);
        UiTheme.styleTextArea(txt_ciphertext, jScrollPane4, false, false);

        UiTheme.styleSecondaryButton(btn_generate);
        UiTheme.styleSecondaryButton(btn_savekeys);
        UiTheme.styleSecondaryButton(btn_loadkeys);
        UiTheme.stylePrimaryButton(btn_encrypt);
        UiTheme.styleSecondaryButton(btn_decrypt);
        UiTheme.setStatusNormal(lbl_status, "Bấm Generate Keys hoặc Load Keys.");
    }

    /** Hiện khóa công khai (Base64) và trạng thái khóa riêng thử nghiệm dạng ẩn lên form. */
    private void showKeys() {
        txt_public.setText(ecc.getPublicKeyBase64());
        txt_private.setText(CryptoSupport.maskPrivateKey(ecc.getPrivateKeyBase64()));
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
        pnl_crypto = new javax.swing.JPanel();
        lbl_sec_crypto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_plaintext = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_ciphertext = new javax.swing.JTextArea();
        btn_encrypt = new javax.swing.JButton();
        btn_decrypt = new javax.swing.JButton();
        pnl_status = new javax.swing.JPanel();
        lbl_status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ECC Cipher");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(36, 36, 36));
        jLabel1.setText("Mật mã đường cong Elliptic (ECC - secp256r1)");

        lbl_subtitle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbl_subtitle.setForeground(new java.awt.Color(102, 102, 102));
        lbl_subtitle.setText("Sơ đồ ECIES: ECDH với khóa tạm, dẫn xuất khóa HKDF-SHA256 và mã hóa xác thực AES-256-GCM.");

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

        pnl_crypto.setBackground(new java.awt.Color(255, 255, 255));
        pnl_crypto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 220, 226)));

        lbl_sec_crypto.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbl_sec_crypto.setForeground(new java.awt.Color(36, 36, 36));
        lbl_sec_crypto.setText("Mã hóa và giải mã");

        jLabel4.setText("Plaintext:");

        txt_plaintext.setColumns(20);
        txt_plaintext.setLineWrap(true);
        txt_plaintext.setRows(2);
        jScrollPane3.setViewportView(txt_plaintext);

        jLabel5.setText("Ciphertext:");

        txt_ciphertext.setColumns(20);
        txt_ciphertext.setLineWrap(true);
        txt_ciphertext.setRows(3);
        jScrollPane4.setViewportView(txt_ciphertext);

        btn_encrypt.setText("Encrypt");
        btn_encrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_encryptActionPerformed(evt);
            }
        });

        btn_decrypt.setText("Decrypt");
        btn_decrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_decryptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_cryptoLayout = new javax.swing.GroupLayout(pnl_crypto);
        pnl_crypto.setLayout(pnl_cryptoLayout);
        pnl_cryptoLayout.setHorizontalGroup(
            pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_cryptoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_sec_crypto)
                    .addGroup(pnl_cryptoLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3))
                    .addGroup(pnl_cryptoLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_cryptoLayout.createSequentialGroup()
                                .addComponent(btn_encrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btn_decrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4))))
                .addGap(16, 16, 16))
        );
        pnl_cryptoLayout.setVerticalGroup(
            pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_cryptoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbl_sec_crypto)
                .addGap(10, 10, 10)
                .addGroup(pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_encrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_decrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(pnl_crypto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(pnl_crypto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(pnl_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generateActionPerformed
        try {
            ecc.generateKeys();
            showKeys();
            UiTheme.setStatusNormal(lbl_status,
                    "Đã tạo cặp khóa mới. Bản mã cũ sẽ không giải mã được với khóa mới.");
        } catch (Exception ex) {
            UiTheme.setStatusError(lbl_status, "Không tạo được khóa: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Không tạo được khóa: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_generateActionPerformed

    private void btn_savekeysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_savekeysActionPerformed
        if (!ecc.hasPublicKey()) {
            JOptionPane.showMessageDialog(this, "Chưa có khóa để lưu.");
            return;
        }
        String content = CryptoSupport.toKeyFile(ecc.getPublicKeyBase64(), ecc.getPrivateKeyBase64());
        String path = FileTextIO.saveString(this, content, "ecc_keys.txt");
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
            ecc.loadKeys(keys[0], keys[1]);
            showKeys();
            UiTheme.setStatusNormal(lbl_status, "Đã nạp khóa từ tệp.");
        } catch (Exception ex) {
            UiTheme.setStatusError(lbl_status, "Tệp khóa không hợp lệ: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Tệp khóa không hợp lệ: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_loadkeysActionPerformed

    private void btn_encryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_encryptActionPerformed
        String plaintext = txt_plaintext.getText();
        if (plaintext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Plaintext trước khi mã hóa.");
            return;
        }
        try {
            String ciphertext = ecc.encrypt(plaintext);
            txt_ciphertext.setText(ciphertext);
            txt_ciphertext.setCaretPosition(0);
            UiTheme.setStatusSuccess(lbl_status,
                    "Đã mã hóa ECC (" + ciphertext.length() + " ký tự Base64). "
                    + HistoryDao.tryInsert("ECC", "ENCRYPT", plaintext.length(), true));
        } catch (Exception ex) {
            String logStatus = HistoryDao.tryInsert("ECC", "ENCRYPT", plaintext.length(), false);
            UiTheme.setStatusError(lbl_status, "Mã hóa ECC thất bại. " + logStatus);
            JOptionPane.showMessageDialog(this, "Mã hóa thất bại: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_encryptActionPerformed

    private void btn_decryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_decryptActionPerformed
        String ciphertext = txt_ciphertext.getText();
        if (ciphertext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Ciphertext trước khi giải mã.");
            return;
        }
        try {
            String plaintext = ecc.decrypt(ciphertext);
            txt_plaintext.setText(plaintext);
            txt_plaintext.setCaretPosition(0);
            UiTheme.setStatusSuccess(lbl_status,
                    "Đã giải mã ECC thành công. "
                    + HistoryDao.tryInsert("ECC", "DECRYPT", ciphertext.length(), true));
        } catch (Exception ex) {
            String logStatus = HistoryDao.tryInsert("ECC", "DECRYPT", ciphertext.length(), false);
            UiTheme.setStatusError(lbl_status,
                    "Giải mã ECC thất bại (sai khóa riêng hoặc bản mã bị sửa). " + logStatus);
            JOptionPane.showMessageDialog(this, "Giải mã thất bại: sai khóa riêng hoặc bản mã bị sửa.\n" + ex,
                    "Giải mã thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_decryptActionPerformed

    public static void main(String args[]) {
        UiTheme.installLookAndFeel();
        java.awt.EventQueue.invokeLater(() -> {
            new frm_ECC().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_decrypt;
    private javax.swing.JButton btn_encrypt;
    private javax.swing.JButton btn_generate;
    private javax.swing.JButton btn_loadkeys;
    private javax.swing.JButton btn_savekeys;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl_sec_crypto;
    private javax.swing.JLabel lbl_sec_keys;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JLabel lbl_subtitle;
    private javax.swing.JPanel pnl_crypto;
    private javax.swing.JPanel pnl_keys;
    private javax.swing.JPanel pnl_status;
    private javax.swing.JTextArea txt_ciphertext;
    private javax.swing.JTextArea txt_plaintext;
    private javax.swing.JTextArea txt_private;
    private javax.swing.JTextArea txt_public;
    // End of variables declaration//GEN-END:variables
}
