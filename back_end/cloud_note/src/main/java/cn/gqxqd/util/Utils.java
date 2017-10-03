package cn.gqxqd.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class Utils {

    public static String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static String md5(String src) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] output = md.digest(src.getBytes());
        String s = Base64.encodeBase64String(output);
        return s;
    }

}
