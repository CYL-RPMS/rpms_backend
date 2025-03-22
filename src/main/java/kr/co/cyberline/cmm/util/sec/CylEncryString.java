package kr.co.cyberline.cmm.util.sec;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;

public class CylEncryString {
    public static String encSHA256(String str) {
        return encProc(str, "SHA-256");
    }

    public static String encMD5(String str) {
        return encProc(str, "MD5");
    }

    private static String encProc(String str, String algorithm) {
        String resultStr = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(str.getBytes());
            byte[] byteData = messageDigest.digest();
            resultStr = Base64.encodeBase64String(byteData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }
}
