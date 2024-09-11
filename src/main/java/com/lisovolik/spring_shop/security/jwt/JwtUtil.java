package com.lisovolik.spring_shop.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.crypto.SecretKey;
import java.util.*;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

public class JwtUtil {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;
    private static final String PRIVATE_KEY = "MIHcAgEBB0ELjXhT8c7yoFjs5eHFvsNYgTrYuVXSWN1KEJqoU";

    public static String generateToken(User user){
        Map<String, Object> claims = new HashMap<>();
        Object[] userRoles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toArray();
        claims.put("Roles",userRoles);
        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .claims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    public static Claims getClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static boolean isTokenValid(String token){
        return !isExpired(token);
    }

    private static boolean isExpired(String token) {
        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private static SecretKey getSigningKey() {
        byte[] keyByte = Decoders.BASE64.decode(PRIVATE_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
