package com.practice.jwt_secret_key_generation;

import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class JwtSecretKeyGenerationApplication {

	public static void main(String[] args) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
		keyGenerator.init(256);

		SecretKey secretKey = keyGenerator.generateKey();
		String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

		System.out.println("===================================");
		System.out.println("Encode Secret key");
		System.out.println("===================================");
		System.out.println(encodedKey);
		System.out.println("===================================");
	}

}
