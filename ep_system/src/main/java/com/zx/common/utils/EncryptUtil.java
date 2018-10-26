package com.zx.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class description
 *
 * @author V.E.
 * @version 1.0, 17/03/15
 */
public class EncryptUtil {

    /**
     * Constructs ...
     */
    private EncryptUtil() {
    }

    /**
     * Method description
     *
     * @param originString
     * @return MD5加密后的字符串
     */
    public static String getMD5Encryption(String originString) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(originString.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}


