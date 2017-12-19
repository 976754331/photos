package com.hr.platform.encrypt;
import java.security.MessageDigest;

import org.bouncycastle.util.encoders.Hex;



/**
 * Created by cash on 2015/9/24.
 */
public class SHA256 {
    /**
     * 将字符串转换为sha256
     * @param inStr
     * @return
     * @throws Exception
     */
    public static String encrypt(String inStr) throws Exception {
        MessageDigest digest;
        digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(inStr.getBytes("UTF-8"));
//        System.out.println(res.length());
        byte[] output = Hex.encode(hash);
        String res = Base64Util.encode(output);
        return res;
    }

    public static void main(String[] arg){
        String a = "abc123你好";
        try {
            System.out.println(encrypt(a));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
