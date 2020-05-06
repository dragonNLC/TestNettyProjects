package com.dragon.test.netty.data.eos;



import com.dragon.test.netty.data.constracts.XMLConstant;
import com.dragondevl.base64.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CipherUtil {

    public static String encroptMode = "ECB";
    public static String paddingMode = "PKCS5Padding";
    public static String algorithm = "DESede";
    public static String charset = "UTF-8";


    /**
     * 加密
     */
    public static String encryptData(String oriData) {
        return encryptData(oriData, XMLConstant.APP_SECRET);
    }


    /**
     * 加密
     */
    public static String encryptData(String oriData, String keyStr) {
        try {
            byte[] keyBytes = Base64.decodeBase64(keyStr);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance(algorithm + "/" + encroptMode + "/" + paddingMode);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptBytes = cipher.doFinal(oriData.getBytes(charset));
            return Base64.encodeBase64String(encryptBytes).replace(" ", "");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 解密
     */
    public static String decryptData(String encData) {
        return decryptData(encData, XMLConstant.APP_SECRET);
    }


    /**
     * 解密
     */
    public static String decryptData(String encData, String keyStr) {
        try {
            byte[] keyBytes = Base64.decodeBase64(keyStr);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance(algorithm + "/" + encroptMode + "/" + paddingMode);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decBytes = cipher.doFinal(Base64.decodeBase64(encData));
            return new String(decBytes, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }


}
