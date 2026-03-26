package com.practice.jwt_secret_key_generation;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;



public class JwtSecretKeyGenerationApplication {
	private final String SECRET_KEY="79LBhZS1YKW+HT8D9+c9lNhd+DqU0OJ9PxWlAa0BvLg=";
    SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

	/*public static void main(String[] args) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
		keyGenerator.init(256);

		SecretKey secretKey = keyGenerator.generateKey();
		String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

		System.out.println("===================================");
		System.out.println("Encode Secret key");
		System.out.println("===================================");
		System.out.println(encodedKey);
		System.out.println("===================================");
	}*/

	public static void main(String[] args) {

        String UserName="Charan";
        String finalToken= new JwtSecretKeyGenerationApplication().generatingTokenUsingSecretKey(UserName);
        System.out.println("===================================");
        System.out.println("Below is the Generated Token: ");
        System.out.println(finalToken);
        System.out.println("===================================");

        System.out.println("Parsing Claims Now: ");
        new JwtSecretKeyGenerationApplication().extractingClaims(finalToken);
    }
    public String generatingTokenUsingSecretKey(String UserName)
    {
        String GeneratedToken = Jwts.builder()
                .subject(UserName)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 *10))
                .signWith(key)
                .compact();
    
        return GeneratedToken;
    
    }

    public void extractingClaims(String token)
    {
        Claims claims = Jwts.parser()   
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
        System.out.println("=======================================");
        System.out.println("Subject: "+claims.getSubject());
        System.out.println("IssuesAt: "+claims.getIssuedAt());
        System.out.println("Expiration: "+claims.getExpiration());
        System.out.println("Issuer : "+claims.getIssuer());
        System.out.println("=======================================");
    }

}
