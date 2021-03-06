package org.cd.sport.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.cd.sport.exception.SportException;

/**
 * rsa加密解密
 * 
 * @author liuyk
 *
 */
public class RSAGenerator {

	private KeyPair keyPair = null;

	public RSAGenerator() {
		this.keyPair = this.initKey();

	}

	private KeyPair initKey() {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			SecureRandom random = new SecureRandom();
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
			generator.initialize(1024, random);
			return generator.generateKeyPair();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成public key
	 * 
	 * @return
	 */
	public String generateBase64PublicKey() {
		RSAPublicKey key = (RSAPublicKey) keyPair.getPublic();
		return new String(Base64.encodeBase64(key.getEncoded()));
	}

	/**
	 * 解密
	 * 
	 * @param string
	 * @return
	 * @throws SportException 
	 */
	public String decryptBase64(String string) throws SportException {
		return new String(decrypt(Base64.decodeBase64(string)));
	}

	private byte[] decrypt(byte[] string) throws SportException {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
			RSAPrivateKey pbk = (RSAPrivateKey) keyPair.getPrivate();
			cipher.init(Cipher.DECRYPT_MODE, pbk);
			byte[] plainText = cipher.doFinal(string);
			return plainText;
		} catch (Exception e) {
			throw new SportException(e.getMessage());
		}
	}

	public static void main(String[] args) {
		// 生成public key
		RSAGenerator rsa = new RSAGenerator();

		System.out.println(rsa.generateBase64PublicKey());
		System.out.println(rsa.generateBase64PublicKey());

		// 解密
		// System.out.println(decryptBase64(
		// "wAfY9JkoKay9SxcPIs1FcG+t6sR+wYwAs/mh9DpfcBraxzqoZdb9LyaAigzFQ0EKck9OyHL0dhv+Uxuw5hHw6CPT0B2Z0i1gwrjDUNaL1gWvqt1pDJVGrIYPLJSjs9xktFhY1jbxQgXGjyCt06Rwid5sJknw90AUO0CyQulfipg="));
	}
}
