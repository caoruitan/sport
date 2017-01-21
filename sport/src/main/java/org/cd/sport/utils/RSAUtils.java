package org.cd.sport.utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * rsa工具类
 * 
 * @author liuyk
 *
 */
public class RSAUtils {
	/**
	 * 通过公钥字符串解析为公钥
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 通过私钥字符串解析为私钥
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * 得到密钥字符串（经过base64编码）
	 * 
	 */
	public static String getKeyString(Key key) throws Exception {
		byte[] keyBytes = key.getEncoded();
		return Base64.encodeBase64String(keyBytes);
	}

	/**
	 * 加密方法
	 * 
	 * @throws Exception
	 * @throws InvalidKeyException
	 */
	public static String encrypt(String msg, String publicKey) throws InvalidKeyException, Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
		byte[] enBytes = cipher.doFinal(msg.getBytes());
		return Base64.encodeBase64String(enBytes);
	}

	/**
	 * 解密方法
	 * 
	 */
	public static String decrypt(String msg, String privateKey) throws InvalidKeyException, Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
		byte[] deBytes = cipher.doFinal(Base64.decodeBase64(msg));
		return new String(deBytes);
	}

	public static void main(String[] args) throws Exception {

		// KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		// // 密钥位数
		// keyPairGen.initialize(1024);
		// // 密钥对
		// KeyPair keyPair = keyPairGen.generateKeyPair();
		// // 公钥
		// PublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		// // 私钥
		// PrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		//
		// String publicKeyString = getKeyString(publicKey);
		// System.out.println("public:\n" + publicKeyString);
		// String privateKeyString = getKeyString(privateKey);
		// System.out.println("private:\n" + privateKeyString);

		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIDyEluJ1dKoaRI3MutPblpyfRL7mlDPgdMlLadW4npxMtqW/2/4w7/5kKACW1Hgu5ovzyWzTjLOZqEFYlpSvGzK4FI9Sxkf3uxrVnS8Gr0a8pzPJF97ZLjNIZXIBtg6h+zylr+je+rr89+DGxt9KPSuWUS2QBwCFJkjmdCCKgFdAgMBAAECgYB0V/ZwWYFwPTvwj5WF83liaoKnqhSmbVR6KsVyg1Z9m72CtVNsuEwG3yq69OVojxk2GYUSQyJ/fPPkvXLshFI1l4dD5Kzrc/ut4dJfV3kdP11SRfApn4+qkcThB0LO+j8lkkROMfxP7Jo5mZeK/PrZxRGGq3Ms/ebKqIod1drwAQJBAOHAosiSZW+QIAvOyejrX7YiK4lD6OCxK8uMjp1CLhu5kBgUc/Q4AmhRIxF4dxdDbS1I9Fuh/arCW7dpN1heJwECQQCSOO+z9dktL5E+66tEZx+ABpYTP4TtQ57fDdaBUfr8rpqQ3j93UeRsYv/Ta6hYFjsMkvS1Cj7BFAi/IsTWW9ZdAkAveWlCMLcwLOjwUr5XQxhPnwP0y9kvV1LUbWyYWZCEuBM7XSG9XAH1mgp75A93TvCos0zf8EL+I/1RpsjCxpoBAkAQthUfGh/h/Ce7ay+9t5NwDFCmjoHgo/PKRkiXqwjmTAY6PExAlMloYcAfJ5SrzI6fF0SEVFFru6BsR8r7PK+5AkEAvu/n8VshN770Myg6sGBXYoEwTwxqV8IuFYXwAu6J6NILyj+BN8f+OjNmhNrn6tQlKiYoib96jQmPR9SxcP4Qrg==";
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCA8hJbidXSqGkSNzLrT25acn0S+5pQz4HTJS2nVuJ6cTLalv9v+MO/+ZCgAltR4LuaL88ls04yzmahBWJaUrxsyuBSPUsZH97sa1Z0vBq9GvKczyRfe2S4zSGVyAbYOofs8pa/o3vq6/PfgxsbfSj0rllEtkAcAhSZI5nQgioBXQIDAQAB";

		String encrypt = encrypt("@Kjsb-main#", publicKey);
		System.out.println("加密后：" + encrypt);

		System.out.println("解密后：" + decrypt(encrypt, privateKey));

	}

}