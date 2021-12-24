package com.tianjian.property.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;

import java.security.Key;

/**
 * APP登录Token的生成和解析
 * 
 */
public class TokenUtil {

	public static final String SECRET = "av55wCCkWAA5ARYB";
	/**
	 * JWT生成Token.<br/>
	 * JWT构成: header, payload, signature
	 *
	 * @param userId 登录成功后用户user_id, 参数user_id不可传空
	 */
	public static String createToken(Integer userId) throws Exception {
		Key key = new AesKey(SECRET.getBytes());
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		jwe.setKey(key);
		jwe.setPayload(userId.toString());
		String token = jwe.getCompactSerialization();
		return token;
	}
	/**
	 * 根据Token获取user_id
	 *
	 * @param token
	 * @return user_id
	 */
	public static Integer getAppUID(String token) throws JoseException {
		Key key = new AesKey(SECRET.getBytes());
		JsonWebEncryption jwe2 = new JsonWebEncryption();
		jwe2.setKey(key);
		jwe2.setCompactSerialization(token);

		final String payload = jwe2.getPayload();
		return Integer.valueOf(payload);
	}

	public static void main(String[] args) throws Exception {
		String filename= RandomStringUtils.randomAlphanumeric(17);
		System.out.println(filename);
	}
}