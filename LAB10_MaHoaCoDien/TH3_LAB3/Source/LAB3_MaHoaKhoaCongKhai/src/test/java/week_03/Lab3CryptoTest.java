package week_03;

import java.nio.charset.StandardCharsets;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Bộ kiểm thử JUnit 5 cho các thuật toán mật mã khóa công khai (Bài 3.1 - 3.5) và lớp tiện ích.
 */
class Lab3CryptoTest {

    @AfterEach
    void cleanupDbConfig() {
        DbConfig.clear();
    }

    // ==================== BÀI 3.1 & 3.2: RSA ====================

    @Test
    @DisplayName("RSA-2048: mã hóa/giải mã HELLO RSA, kiểm tra độ dài Base64 = 344 và giới hạn 190 byte")
    void testRsa2048BasicAndBoundary() throws Exception {
        RSACipher rsa = new RSACipher();
        assertThrows(IllegalStateException.class, () -> rsa.encrypt("HELLO RSA"));

        rsa.generateKeys(2048);
        assertTrue(rsa.hasKeys());
        assertEquals(2048, rsa.getBits());
        assertEquals(190, rsa.maxPlaintextBytes());
        assertEquals(392, rsa.getPublicKeyBase64().length());

        String cipher = rsa.encrypt("HELLO RSA");
        assertEquals(344, cipher.length());
        assertEquals("HELLO RSA", rsa.decrypt(cipher));

        // Đúng biên 190 byte
        String exact190 = "A".repeat(190);
        String c190 = rsa.encrypt(exact190);
        assertEquals(344, c190.length());
        assertEquals(exact190, rsa.decrypt(c190));

        // Vượt biên 191 byte -> IllegalArgumentException
        String over191 = "A".repeat(191);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> rsa.encrypt(over191));
        assertTrue(ex.getMessage().contains("190"));
    }

    @Test
    @DisplayName("RSA-3072: mã hóa/giải mã HELLO RSA, kiểm tra độ dài Base64 = 512 và giới hạn 318 byte")
    void testRsa3072BasicAndBoundary() throws Exception {
        RSACipher rsa = new RSACipher();
        rsa.generateKeys(3072);
        assertEquals(3072, rsa.getBits());
        assertEquals(318, rsa.maxPlaintextBytes());

        String cipher = rsa.encrypt("HELLO RSA");
        assertEquals(512, cipher.length());
        assertEquals("HELLO RSA", rsa.decrypt(cipher));

        // Đúng biên 318 byte
        String exact318 = "B".repeat(318);
        assertEquals(exact318, rsa.decrypt(rsa.encrypt(exact318)));

        // Vượt biên 319 byte -> IllegalArgumentException
        String over319 = "B".repeat(319);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> rsa.encrypt(over319));
        assertTrue(ex.getMessage().contains("318"));
    }

    @Test
    @DisplayName("RSA-OAEP: hai lần mã hóa cùng bản rõ cho hai bản mã khác nhau, đều giải mã đúng")
    void testRsaOaepRandomnessAndWrongKey() throws Exception {
        RSACipher rsa1 = new RSACipher();
        rsa1.generateKeys(2048);

        String c1 = rsa1.encrypt("HELLO RSA");
        String c2 = rsa1.encrypt("HELLO RSA");
        assertNotEquals(c1, c2, "OAEP phải tạo bản mã khác nhau ở mỗi lần mã hóa");
        assertEquals("HELLO RSA", rsa1.decrypt(c1));
        assertEquals("HELLO RSA", rsa1.decrypt(c2));

        // Giải mã bằng khóa sai -> lỗi
        RSACipher rsa2 = new RSACipher();
        rsa2.generateKeys(2048);
        assertThrows(BadPaddingException.class, () -> rsa2.decrypt(c1));

        // Kích thước khóa không hợp lệ
        assertThrows(IllegalArgumentException.class, () -> rsa1.generateKeys(1024));
    }

    // ==================== BÀI 3.3: ECC (ECIES) ====================

    @Test
    @DisplayName("ECC secp256r1: mã hóa/giải mã HELLO ECC, lưu/nạp khóa và tính ngẫu nhiên")
    void testEccEncryptDecryptAndSaveLoadKeys() throws Exception {
        ECCCipher ecc = new ECCCipher();
        assertThrows(IllegalStateException.class, () -> ecc.encrypt("HELLO ECC"));

        ecc.generateKeys();
        assertTrue(ecc.hasPublicKey());
        assertTrue(ecc.hasPrivateKey());
        assertEquals(124, ecc.getPublicKeyBase64().length());

        String c1 = ecc.encrypt("HELLO ECC");
        String c2 = ecc.encrypt("HELLO ECC");
        assertNotEquals(c1, c2, "Mỗi lần mã hóa ECC phải sinh khóa tạm, salt và nonce mới");
        assertEquals("HELLO ECC", ecc.decrypt(c1));
        assertEquals("HELLO ECC", ecc.decrypt(c2));

        // Lưu và nạp lại khóa vào đối tượng mới -> giải mã lại bản mã cũ
        String keyFile = CryptoSupport.toKeyFile(ecc.getPublicKeyBase64(), ecc.getPrivateKeyBase64());
        String[] parsed = CryptoSupport.fromKeyFile(keyFile);

        ECCCipher reloaded = new ECCCipher();
        reloaded.loadKeys(parsed[0], parsed[1]);
        assertEquals("HELLO ECC", reloaded.decrypt(c1));

        // Chỉ có Public Key -> mã hóa được nhưng không giải mã được
        ECCCipher publicOnly = new ECCCipher();
        publicOnly.loadKeys(parsed[0], "");
        String cFromPubOnly = publicOnly.encrypt("HELLO ECC");
        assertThrows(IllegalStateException.class, () -> publicOnly.decrypt(cFromPubOnly));
        assertEquals("HELLO ECC", reloaded.decrypt(cFromPubOnly));
    }

    @Test
    @DisplayName("ECC: giải mã sai khóa và bản mã bị sửa đều thất bại với AEADBadTagException")
    void testEccWrongKeyAndTamperedCiphertext() throws Exception {
        ECCCipher ecc1 = new ECCCipher();
        ecc1.generateKeys();
        String cipher = ecc1.encrypt("HELLO ECC");

        // Khóa sai
        ECCCipher ecc2 = new ECCCipher();
        ecc2.generateKeys();
        assertThrows(AEADBadTagException.class, () -> ecc2.decrypt(cipher));

        // Sửa 1 ký tự trong phần dữ liệu AES-GCM (cuối bản mã)
        int pos = cipher.length() - 6;
        char flipped = cipher.charAt(pos) == 'A' ? 'B' : 'A';
        String tampered = cipher.substring(0, pos) + flipped + cipher.substring(pos + 1);
        assertThrows(AEADBadTagException.class, () -> ecc1.decrypt(tampered));
    }

    // ==================== BÀI 3.4: HỆ MÃ LAI RSA + AES ====================

    @Test
    @DisplayName("RSA + AES: mã hóa/giải mã văn bản tiếng Việt > 1.000 ký tự, lưu/nạp khóa và phát hiện sửa bản mã")
    void testRsaAesLongTextAndTamperDetection() throws Exception {
        RSA_AES_Cipher hybrid = new RSA_AES_Cipher();
        hybrid.generateKeys();

        String unit = "Hệ mã lai RSA + AES-GCM: AES-256-GCM mã hóa toàn bộ văn bản dài nhanh chóng và xác thực toàn vẹn, còn RSA-OAEP-SHA256 chỉ bọc khóa phiên AES 32 byte. ";
        StringBuilder sb = new StringBuilder();
        while (sb.length() <= 1100) {
            sb.append(unit);
        }
        String longText = sb.toString();
        assertTrue(longText.length() > 1000);
        assertTrue(longText.getBytes(StandardCharsets.UTF_8).length > 1000);

        String c1 = hybrid.encrypt(longText);
        String c2 = hybrid.encrypt(longText);
        assertNotEquals(c1, c2);
        assertEquals(longText, hybrid.decrypt(c1));
        assertEquals(longText, hybrid.decrypt(c2));

        // Lưu & nạp khóa vào phiên mới -> giải mã lại thành công
        String keyFile = CryptoSupport.toKeyFile(hybrid.getPublicKeyBase64(), hybrid.getPrivateKeyBase64());
        String[] keys = CryptoSupport.fromKeyFile(keyFile);
        RSA_AES_Cipher reloaded = new RSA_AES_Cipher();
        reloaded.loadKeys(keys[0], keys[1]);
        assertEquals(longText, reloaded.decrypt(c1));

        // Sửa 1 ký tự ở giữa phần dữ liệu AES -> AEADBadTagException
        int mid = c1.length() / 2;
        char flipped = c1.charAt(mid) == 'A' ? 'B' : 'A';
        String tampered = c1.substring(0, mid) + flipped + c1.substring(mid + 1);
        assertThrows(AEADBadTagException.class, () -> hybrid.decrypt(tampered));

        // Giải mã bằng khóa khác -> thất bại
        RSA_AES_Cipher other = new RSA_AES_Cipher();
        other.generateKeys();
        assertThrows(BadPaddingException.class, () -> other.decrypt(c1));
    }

    // ==================== BÀI 3.5: CHỮ KÝ SỐ ECDSA ====================

    @Test
    @DisplayName("ECDSA SHA256withECDSA: VALID, INVALID khi sửa thông điệp, đổi khóa và chế độ chỉ có Public Key")
    void testEcdsaAllMandatoryCases() throws Exception {
        ECCSignature signer = new ECCSignature();
        signer.generateKeys();

        String msgA = "THONG DIEP A";
        String msgB = "THONG DIEP B";
        String sig = signer.sign(msgA);

        // 1. THONG DIEP A + chữ ký đúng -> VALID
        assertTrue(signer.verify(msgA, sig));

        // 2. Sửa thành THONG DIEP B -> INVALID
        assertFalse(signer.verify(msgB, sig));

        // 3. Khôi phục THONG DIEP A -> VALID
        assertTrue(signer.verify(msgA, sig));

        // 4. Đổi khóa công khai xác minh -> INVALID
        ECCSignature otherUser = new ECCSignature();
        otherUser.generateKeys();
        assertFalse(otherUser.verify(msgA, sig));

        // 5. Mô phỏng 2 người: chỉ có Public Key -> Verify VALID, Sign bị từ chối
        String publicOnlyFile = CryptoSupport.toKeyFile(signer.getPublicKeyBase64(), "");
        String[] keys = CryptoSupport.fromKeyFile(publicOnlyFile);
        ECCSignature verifierOnly = new ECCSignature();
        verifierOnly.loadKeys(keys[0], keys[1]);

        assertTrue(verifierOnly.verify(msgA, sig));
        assertFalse(verifierOnly.verify(msgB, sig));
        assertThrows(IllegalStateException.class, () -> verifierOnly.sign(msgA));

        // Chữ ký bị hỏng định dạng -> INVALID
        assertFalse(verifierOnly.verify(msgA, CryptoSupport.b64(new byte[] {1, 2, 3, 4})));
    }

    // ==================== TIỆN ÍCH & TRẠNG THÁI CHƯA KẾT NỐI SQL ====================

    @Test
    @DisplayName("CryptoSupport, DbConfig & HistoryDao khi chưa kết nối SQL")
    void testSupportAndOfflineHistoryBehavior() {
        assertThrows(IllegalArgumentException.class, () -> CryptoSupport.unb64("@@@not-base64@@@"));
        assertThrows(IllegalArgumentException.class, () -> CryptoSupport.fromKeyFile("INVALID_CONTENT"));

        String masked = CryptoSupport.maskPrivateKey("SAMPLE_BASE64_KEY");
        assertFalse(masked.contains("SAMPLE_BASE64_KEY"));
        assertTrue(CryptoSupport.maskPrivateKey("").contains("Không có khóa riêng"));

        // Kiểm tra ràng buộc DbConfig
        assertThrows(IllegalArgumentException.class,
                () -> DbConfig.set("", 14333, "LAB3_MaHoaKhoaCongKhai", "lab3_user", new char[0]));
        assertThrows(IllegalArgumentException.class,
                () -> DbConfig.set("127.0.0.1", 70000, "LAB3_MaHoaKhoaCongKhai", "lab3_user", new char[0]));

        // Khi chưa kết nối SQL Server -> tryInsert KHÔNG được báo đã lưu
        DbConfig.clear();
        assertFalse(DbConfig.isReady());
        String msg = HistoryDao.tryInsert("RSA", "ENCRYPT", 9, true);
        assertTrue(msg.contains("Chưa kết nối SQL Server"));

        DefaultTableModel model = HistoryDao.emptyModel();
        assertEquals(6, model.getColumnCount());
        assertFalse(model.isCellEditable(0, 0));
    }
}
