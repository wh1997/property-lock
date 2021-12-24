package com.tianjian.property.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\8\7 0007  11:19
 * @description RSA加解密工具类
 */
public class RSAUtils {
    //非对称密钥算法
    public static final String RSA_KEY_ALGORITHM = "RSA";

    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int RSA_KEY_SIZE = 512;

    //公钥
    private static final String RSA_PUBLIC_KEY = "PublicKey";

    //私钥
    private static final String RSA_PRIVATE_KEY = "PrivateKey";

    /**
     * 初始化密钥对
     * @return
     * @throws Exception
     */
    public static KeyPair initKey() throws Exception {
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_KEY_ALGORITHM);
        // 设置随机数用于安全加密
//        SecureRandom random = new SecureRandom();
        //初始化密钥生成器
        keyPairGenerator.initialize(RSA_KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }
    public static void main(String[] args) throws Exception {
        String privateKey = RSAUtils.getPrivateKey(initKey());
        System.out.println(privateKey);
    }
    /**
     * 获取私钥字符串
     * @param keyPair
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(KeyPair keyPair) throws Exception {
        return getKeyString((RSAPrivateKey) keyPair.getPrivate());
    }

    /**
     * 获取公钥字符串
     * @param keyPair
     * @return
     * @throws Exception
     */
    public static String getPublicKey(KeyPair keyPair) throws Exception {
        return getKeyString((RSAPublicKey) keyPair.getPublic());
    }

    /**
     * 获取秘钥字符串（经过base64编码）
     * @param key
     * @return
     */
    public static String getKeyString(Key key) {
        // 获取key
        byte[] keyBytes = key.getEncoded();
        return Base64.encodeBase64String(keyBytes);
    }


    /**
     * 私钥加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(String data, String key) throws Exception {
        return dealDataByPrivateKey(data.getBytes(), Base64.decodeBase64(key), Cipher.ENCRYPT_MODE);
    }

    /**
     * 私钥解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(String data, String key) throws Exception {
        return dealDataByPrivateKey(Base64.decodeBase64(data), Base64.decodeBase64(key), Cipher.DECRYPT_MODE);
    }

    /**
     * 私钥数据加解密处理
     * @param data
     * @param key
     * @param encryptMode
     * @return
     */
    private static byte[] dealDataByPrivateKey(byte[] data, byte[] key, int encryptMode) throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据处理
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(encryptMode, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密数据
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String data, String key) throws Exception {
        return dealDataByPublicKey(data.getBytes(), Base64.decodeBase64(key), Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密公钥数据
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(String data, String key) throws Exception {
        return dealDataByPublicKey(data.getBytes(), Base64.decodeBase64(key), Cipher.DECRYPT_MODE);
    }

    /**
     * 公钥数据加解密处理
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] dealDataByPublicKey(byte[] data, byte[] key, int encryptMode) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

        //数据加解密处理
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

}
