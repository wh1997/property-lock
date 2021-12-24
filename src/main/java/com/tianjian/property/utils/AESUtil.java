package com.tianjian.property.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/12/1
 */
public class AESUtil {

    public static String encryptKey(String tokenId, String pw) throws Exception {

// MD5加密

        byte[] md5Byte = MessageDigest.getInstance("MD5").digest(tokenId.getBytes());

// 转为16进制字符串

        String hexStr = bytesToHex(md5Byte);

// 截取

        String aesKey = hexStr.substring(8, 24);

// AES加密，返回结果

        return encrypt(pw, aesKey, aesKey);

    }

    private static String bytesToHex(byte[] bytes) {

        StringBuilder sb = new StringBuilder(bytes.length * 2);

        for (int i = 0; i < bytes.length; i++) {

            sb.append(Character.toUpperCase(Character.forDigit((bytes[i] >> 4) & 0x0F, 16)));

            sb.append(Character.toUpperCase(Character.forDigit(bytes[i] & 0x0F, 16)));

        }

        return sb.toString();

    }

    private static String encrypt(String pw, String aesKey, String viPara) throws Exception {

        IvParameterSpec zeroIv = new IvParameterSpec(viPara.getBytes());

        SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);

        return Base64.getEncoder().encodeToString(cipher.doFinal(pw.getBytes("UTF-8")));

    }
}