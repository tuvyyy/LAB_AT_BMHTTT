package week_01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CipherTest {

    @Test
    public void testCaesar() {
        assertEquals("KHOOR", CaesarCipher.encrypt("HELLO", 3));
        assertEquals("HELLO", CaesarCipher.decrypt("KHOOR", 3));
        // Wrap-around and case preservation
        assertEquals("Khoor, Zruog!", CaesarCipher.encrypt("Hello, World!", 3));
        assertEquals("Hello, World!", CaesarCipher.decrypt("Khoor, Zruog!", 3));
    }

    @Test
    public void testVigenere() {
        assertEquals("RIJVS", VigenereCipher.encrypt("HELLO", "KEY"));
        assertEquals("HELLO", VigenereCipher.decrypt("RIJVS", "KEY"));
        // Invalid key
        assertThrows(IllegalArgumentException.class, () -> VigenereCipher.encrypt("HELLO", "K3Y"));
    }

    @Test
    public void testRailFence() {
        assertEquals("HOREL OLLWD", RailFenceCipher.encrypt("HELLO WORLD", 3));
        assertEquals("HELLO WORLD", RailFenceCipher.decrypt("HOREL OLLWD", 3));
        // Invalid rails
        assertThrows(IllegalArgumentException.class, () -> RailFenceCipher.encrypt("HELLO WORLD", 1));
        assertThrows(IllegalArgumentException.class, () -> RailFenceCipher.encrypt("HELLO WORLD", 1001));
    }

    @Test
    public void testPlayfair() {
        assertEquals("GYIZSCOKCFBU", PlayfairCipher.encrypt("HELLOWORLD", "KEYWORD"));
        assertEquals("HELXLOWORLDX", PlayfairCipher.decrypt("GYIZSCOKCFBU", "KEYWORD"));
        // Invalid key
        assertThrows(IllegalArgumentException.class, () -> PlayfairCipher.encrypt("HELLO", "KEY WORD"));
    }

    @Test
    public void testTransposition() {
        int[] key = TranspositionCipher.parseKey("2,3,1");
        assertArrayEquals(new int[]{2, 3, 1}, key);
        assertEquals("AAOTBM", TranspositionCipher.encrypt("BAOMAT", key));
        assertEquals("BAOMAT", TranspositionCipher.decrypt("AAOTBM", key));
        // Invalid keys
        assertThrows(IllegalArgumentException.class, () -> TranspositionCipher.parseKey("1,1,2"));
        assertThrows(IllegalArgumentException.class, () -> TranspositionCipher.parseKey("abc"));
    }

    @Test
    public void testAutokey() {
        assertEquals("RIJSS", AutokeyCipher.encrypt("HELLO", "KEY"));
        assertEquals("HELLO", AutokeyCipher.decrypt("RIJSS", "KEY"));
        // Invalid key
        assertThrows(IllegalArgumentException.class, () -> AutokeyCipher.encrypt("HELLO", "123"));
    }

    @Test
    public void testCipherTongHop() {
        assertEquals("KHOOR", CipherTongHop.encrypt("Caesar", "HELLO", "3"));
        assertEquals("HELLO", CipherTongHop.decrypt("Caesar", "KHOOR", "3"));

        assertEquals("RIJVS", CipherTongHop.encrypt("Vigenere", "HELLO", "KEY"));
        assertEquals("HELLO", CipherTongHop.decrypt("Vigenere", "RIJVS", "KEY"));

        assertEquals("HOREL OLLWD", CipherTongHop.encrypt("Rail Fence", "HELLO WORLD", "3"));
        assertEquals("HELLO WORLD", CipherTongHop.decrypt("Rail Fence", "HOREL OLLWD", "3"));

        assertEquals("GYIZSCOKCFBU", CipherTongHop.encrypt("Playfair", "HELLOWORLD", "KEYWORD"));
        assertEquals("HELXLOWORLDX", CipherTongHop.decrypt("Playfair", "GYIZSCOKCFBU", "KEYWORD"));

        assertEquals("AAOTBM", CipherTongHop.encrypt("Transposition", "BAOMAT", "2,3,1"));
        assertEquals("BAOMAT", CipherTongHop.decrypt("Transposition", "AAOTBM", "2,3,1"));

        assertEquals("RIJSS", CipherTongHop.encrypt("Autokey", "HELLO", "KEY"));
        assertEquals("HELLO", CipherTongHop.decrypt("Autokey", "RIJSS", "KEY"));
    }

    @Test
    public void testAttackDemos() {
        String bf = AttackDemos.caesarBruteForce("KHOOR");
        assertTrue(bf.contains("khóa k = 3"));
        assertTrue(bf.contains("HELLO"));

        String freq = AttackDemos.frequency("PHHW PH DIWHU WKH WRJD SDUWB");
        assertTrue(freq.contains("Tổng số chữ: 23"));

        String kp = AttackDemos.knownPlaintext("HELLO", "RIJVS");
        assertEquals("KEYKE", kp);
    }
}
