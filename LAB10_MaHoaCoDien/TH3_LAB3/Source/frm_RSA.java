package week_03;

import java.awt.Cursor;
import javax.swing.JOptionPane;

public class frm_RSA extends javax.swing.JFrame {

    static {
        UiTheme.installLookAndFeel();
    }

    /** Đối tượng RSA giữ cặp khóa trong suốt thời gian form mở. */
    private final RSACipher rsa = new RSACipher();

    public frm_RSA() {
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
        UiTheme.styleSecondaryLabel(lbl_keyinfo);

        UiTheme.styleComboBox(cbo_bits);
        UiTheme.styleTextArea(txt_plaintext, jScrollPane1, false, true);
        UiTheme.styleTextArea(txt_ciphertext, jScrollPane2, false, false);

        UiTheme.styleSecondaryButton(btn_generate);
        UiTheme.stylePrimaryButton(btn_encrypt);
        UiTheme.styleSecondaryButton(btn_decrypt);
        UiTheme.setStatusNormal(lbl_status, "Bấm Generate Keys để tạo cặp khóa.");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbl_subtitle = new javax.swing.JLabel();
        pnl_keys = new javax.swing.JPanel();
        lbl_sec_keys = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbo_bits = new javax.swing.JComboBox<>();
        btn_generate = new javax.swing.JButton();
        lbl_keyinfo = new javax.swing.JLabel();
        pnl_crypto = new javax.swing.JPanel();
        lbl_sec_crypto = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_plaintext = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_ciphertext = new javax.swing.JTextArea();
        btn_encrypt = new javax.swing.JButton();
        btn_decrypt = new javax.swing.JButton();
        pnl_status = new javax.swing.JPanel();
        lbl_status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("RSA Cipher");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(36, 36, 36));
        jLabel1.setText("Mật mã RSA (OAEP - SHA-256)");

        lbl_subtitle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbl_subtitle.setForeground(new java.awt.Color(102, 102, 102));
        lbl_subtitle.setText("Mã hóa bằng khóa công khai, giải mã bằng khóa riêng; hỗ trợ RSA 2048 và 3072 bit.");

        pnl_keys.setBackground(new java.awt.Color(255, 255, 255));
        pnl_keys.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 220, 226)));

        lbl_sec_keys.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbl_sec_keys.setForeground(new java.awt.Color(36, 36, 36));
        lbl_sec_keys.setText("Thiết lập khóa");

        jLabel2.setText("Key size:");

        cbo_bits.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2048", "3072" }));

        btn_generate.setText("Generate Keys");
        btn_generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generateActionPerformed(evt);
            }
        });

        lbl_keyinfo.setText("Chưa có khóa.");

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
                        .addComponent(cbo_bits, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btn_generate, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(lbl_keyinfo, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        pnl_keysLayout.setVerticalGroup(
            pnl_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_keysLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbl_sec_keys)
                .addGap(10, 10, 10)
                .addGroup(pnl_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_bits, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_generate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_keyinfo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        pnl_crypto.setBackground(new java.awt.Color(255, 255, 255));
        pnl_crypto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 220, 226)));

        lbl_sec_crypto.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbl_sec_crypto.setForeground(new java.awt.Color(36, 36, 36));
        lbl_sec_crypto.setText("Mã hóa và giải mã");

        jLabel3.setText("Plaintext:");

        txt_plaintext.setColumns(20);
        txt_plaintext.setLineWrap(true);
        txt_plaintext.setRows(3);
        jScrollPane1.setViewportView(txt_plaintext);

        jLabel4.setText("Ciphertext:");

        txt_ciphertext.setColumns(20);
        txt_ciphertext.setLineWrap(true);
        txt_ciphertext.setRows(4);
        jScrollPane2.setViewportView(txt_ciphertext);

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
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1))
                    .addGroup(pnl_cryptoLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_cryptoLayout.createSequentialGroup()
                                .addComponent(btn_encrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btn_decrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))))
                .addGap(16, 16, 16))
        );
        pnl_cryptoLayout.setVerticalGroup(
            pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_cryptoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbl_sec_crypto)
                .addGap(10, 10, 10)
                .addGroup(pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(pnl_cryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_encrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_decrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pnl_status.setBackground(new java.awt.Color(255, 255, 255));
        pnl_status.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 220, 226)));

        lbl_status.setText("Bấm Generate Keys để tạo cặp khóa.");

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
        int bits = Integer.parseInt((String) cbo_bits.getSelectedItem());
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            long start = System.currentTimeMillis();
            rsa.generateKeys(bits);
            long ms = System.currentTimeMillis() - start;
            lbl_keyinfo.setText("RSA-" + bits + ": tạo khóa " + ms + " ms, tối đa "
                    + rsa.maxPlaintextBytes() + " byte");
            UiTheme.setStatusNormal(lbl_status,
                    "Đã tạo cặp khóa. Khóa công khai dùng để mã hóa, khóa riêng để giải mã.");
        } catch (Exception ex) {
            UiTheme.setStatusError(lbl_status, "Không tạo được khóa: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Không tạo được khóa: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btn_generateActionPerformed

    private void btn_encryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_encryptActionPerformed
        String plaintext = txt_plaintext.getText();
        if (plaintext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Plaintext trước khi mã hóa.");
            return;
        }
        try {
            String ciphertext = rsa.encrypt(plaintext);
            txt_ciphertext.setText(ciphertext);
            txt_ciphertext.setCaretPosition(0);
            UiTheme.setStatusSuccess(lbl_status,
                    "Đã mã hóa RSA (" + ciphertext.length() + " ký tự Base64). "
                    + HistoryDao.tryInsert("RSA", "ENCRYPT", plaintext.length(), true));
        } catch (Exception ex) {
            String logStatus = HistoryDao.tryInsert("RSA", "ENCRYPT", plaintext.length(), false);
            UiTheme.setStatusError(lbl_status, "Mã hóa RSA thất bại. " + logStatus);
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
            String plaintext = rsa.decrypt(ciphertext);
            txt_plaintext.setText(plaintext);
            txt_plaintext.setCaretPosition(0);
            UiTheme.setStatusSuccess(lbl_status,
                    "Đã giải mã RSA thành công. "
                    + HistoryDao.tryInsert("RSA", "DECRYPT", ciphertext.length(), true));
        } catch (Exception ex) {
            String logStatus = HistoryDao.tryInsert("RSA", "DECRYPT", ciphertext.length(), false);
            UiTheme.setStatusError(lbl_status,
                    "Giải mã RSA thất bại (sai khóa riêng hoặc bản mã bị sửa). " + logStatus);
            JOptionPane.showMessageDialog(this, "Giải mã thất bại: sai khóa riêng hoặc bản mã bị sửa.\n" + ex,
                    "Giải mã thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_decryptActionPerformed

    public static void main(String args[]) {
        UiTheme.installLookAndFeel();
        java.awt.EventQueue.invokeLater(() -> {
            new frm_RSA().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_decrypt;
    private javax.swing.JButton btn_encrypt;
    private javax.swing.JButton btn_generate;
    private javax.swing.JComboBox<String> cbo_bits;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_keyinfo;
    private javax.swing.JLabel lbl_sec_crypto;
    private javax.swing.JLabel lbl_sec_keys;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JLabel lbl_subtitle;
    private javax.swing.JPanel pnl_crypto;
    private javax.swing.JPanel pnl_keys;
    private javax.swing.JPanel pnl_status;
    private javax.swing.JTextArea txt_ciphertext;
    private javax.swing.JTextArea txt_plaintext;
    // End of variables declaration//GEN-END:variables
}
