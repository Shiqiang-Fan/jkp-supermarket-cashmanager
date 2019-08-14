package com.joyveb.cashmanage.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;

@Slf4j
public class DESCoder {

	private static final String TAG = "DESCoder";
	private final static String CHARSET = "utf-8";

	/**
	 * 对cipherText进行DES解密
	 * 
	 * @param cipherText
	 * @return
	 * @throws DesException
	 */
	public static String desDecrypt(String cipherText, String desKey){
		String decryptStr = null;
		try {
			// 解密
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

			byte[] input = Base64.decodeBase64(cipherText.trim().getBytes(
					CHARSET));

			cipher.init(Cipher.DECRYPT_MODE, genSecretKey(desKey));
			byte[] output = cipher.doFinal(input);
			decryptStr = new String(output, CHARSET);

		} catch (Exception e) {
			log.debug(TAG, "DES解密发生异常!" + e);
		}
		return decryptStr;
	}

	/**
	 * 对message进行DES加密
	 * 
	 * @param message
	 * @return
	 * @throws DesException
	 */
	public static String desEncrypt(String message, String desKey) {
		String encryptStr = null;
		byte encryptByte[];

		try {
			// 加密
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, genSecretKey(desKey));
			byte[] cipherText = cipher
					.doFinal(message.trim().getBytes(CHARSET));
			encryptByte = Base64.encodeBase64(cipherText);
			encryptStr = new String(encryptByte, CHARSET);
			encryptStr = encryptStr.replaceAll("\r\n", "").replaceAll("\n", "");

		} catch (Exception e) {
			log.debug(TAG, "DES加密发生异常!" + e);
		}
		return encryptStr;
	}

	private static SecretKey genSecretKey(String key)
			throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException {
		DESKeySpec desKeySpec = new DESKeySpec(hexStringToByte(key));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

		return secretKey;
	}

	private static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}
}
