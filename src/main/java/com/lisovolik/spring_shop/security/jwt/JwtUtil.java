package com.lisovolik.spring_shop.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

public class JwtUtil {
    //@Value("${jwt.token.validity}")
    private long JWT_TOKEN_VALIDITY = 432000000;

    //@Value("${jwt.signing.key}")
    private String PRIVATE_KEY = "MIHcAgEBB0ELjXhT8c7yoFjs5eHFvsNYgTrYuVXSWN1KEJqoU";


    public String generateToken(User user){
        Map<String, Object> claims = new HashMap<>();
        Object[] userRoles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toArray();
        claims.put("Roles",userRoles);
        //Long validMillis = env.getProperty("jwt.token.validity", Long.class);
        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .claims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    public Claims getClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getSigningKey() )
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token){
        return !isExpired(token);
    }

    private boolean isExpired(String token) {
        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private SecretKey getSigningKey() {
        //String privateKey = env.getProperty("jwt.signing.key");
        byte[] keyByte = Decoders.BASE64.decode(PRIVATE_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
