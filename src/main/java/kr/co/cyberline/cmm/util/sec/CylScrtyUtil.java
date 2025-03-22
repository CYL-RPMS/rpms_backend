package kr.co.cyberline.cmm.util.sec;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import org.apache.commons.codec.binary.Base64;

public class CylScrtyUtil {
    public static String genRdmPassword(int passwordLength) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while (true) {
            int randomInt = random.nextInt(122) + 1;
            if (randomInt >= 48 && randomInt <= 57) {
                sb.append((char)randomInt);
            } else if (randomInt >= 65 && randomInt <= 90) {
                sb.append((char)randomInt);
            } else if (randomInt >= 97 && randomInt <= 122) {
                sb.append((char)randomInt);
            } else {
                continue;
            }
            if (sb.length() >= passwordLength)
                break;
        }
        return sb.toString();
    }

    public static String genRdmPasswordMD5() {
        return CylEncryString.encMD5(genRdmPassword(10));
    }

    public static String genRdmPasswordSHA() {
        return CylEncryString.encSHA256(genRdmPassword(10));
    }

    public static String encodeBinary(byte[] data) {
        if (data == null)
            return "";
        return new String(Base64.encodeBase64(data));
    }

    @Deprecated
    public static String encode(String data) {
        return encodeBinary(data.getBytes());
    }

    public static byte[] decodeBinary(String data) {
        return Base64.decodeBase64(data.getBytes());
    }

    @Deprecated
    public static String decode(String data) {
        return new String(decodeBinary(data));
    }

    @Deprecated
    public static String encryptPassword(String data) throws NoSuchAlgorithmException {
        if (data == null)
            return "";
        byte[] plainText = null;
        byte[] hashValue = null;
        plainText = data.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        hashValue = md.digest(plainText);
        return new String(Base64.encodeBase64(hashValue));
    }

    public static String encryptPassword(String password, String id) throws NoSuchAlgorithmException {
        if (password == null)
            return "";
        byte[] hashValue = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(id.getBytes());
        hashValue = md.digest(password.getBytes());
        return new String(Base64.encodeBase64(hashValue));
    }

    public static String encryptPassword(String data, byte[] salt) throws NoSuchAlgorithmException {
        if (data == null)
            return "";
        byte[] hashValue = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(salt);
        hashValue = md.digest(data.getBytes());
        return new String(Base64.encodeBase64(hashValue));
    }

    public static boolean checkPassword(String data, String encoded, byte[] salt) throws NoSuchAlgorithmException {
        byte[] hashValue = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(salt);
        hashValue = md.digest(data.getBytes());
        return MessageDigest.isEqual(hashValue, Base64.decodeBase64(encoded.getBytes()));
    }
}
