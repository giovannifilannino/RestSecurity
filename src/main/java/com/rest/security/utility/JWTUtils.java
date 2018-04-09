package com.rest.security.utility;

import java.security.Key;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.UriInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {

	private static final int SECRET_STARTING_OFFSET_INCLUSIVE = 0;
    private static final long TOKEN_EXPIRATION_IN_MINUTES = 180L;
    private static final String ENCRYPTION_ALGORITHM = "DES";
    private static final Key JWT_SECRET;
    private static final String USER = "user";

    static {
        final char[] secret = "3s3mp10".toCharArray();
        JWT_SECRET = new SecretKeySpec(new String(secret).getBytes(), SECRET_STARTING_OFFSET_INCLUSIVE, secret.length, ENCRYPTION_ALGORITHM);
    }

    public static String generateToken(UriInfo uriInfo, Principal user) {
        return Jwts.builder()
                .claim(USER, user)
                .setSubject(user.getName())
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from((LocalDateTime.now().plusMinutes(TOKEN_EXPIRATION_IN_MINUTES)).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public static String generateToken(String absolutePath, Principal user) {
        return Jwts.builder()
                .claim(USER, user)
                .setSubject(user.getName())
                .setIssuer(absolutePath)
                .setIssuedAt(new Date())
                .setExpiration(Date.from((LocalDateTime.now().plusMinutes(TOKEN_EXPIRATION_IN_MINUTES)).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public static void verifyToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(JWTUtils.JWT_SECRET)
                    .parseClaimsJws(token);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    public static Claims getTokenPayload(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
