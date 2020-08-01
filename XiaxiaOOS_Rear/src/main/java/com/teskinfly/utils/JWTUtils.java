package com.teskinfly.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtils {
    String key;
    Long ttl;

    public JWTUtils(String key, Long ttl) {
        this.key = key;
        this.ttl = ttl;
    }

    public String create(Integer id, String name) {
        long end = System.currentTimeMillis() + ttl;
        JwtBuilder builder = Jwts.builder().setId(id+"")
                .setSubject("name")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,key);
//        builder.setExpiration(new Date(end));
        return builder.compact();
    }
    public Claims parse(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
