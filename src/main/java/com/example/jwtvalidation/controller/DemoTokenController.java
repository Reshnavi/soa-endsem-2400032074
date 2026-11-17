package com.example.jwtvalidation.controller;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@RestController
@RequestMapping("/api/demo")
public class DemoTokenController {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @GetMapping("/token")
    public Map<String, String> generateDemoToken() {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + 60 * 60 * 1000);

        String token = Jwts.builder()
                .subject("demoUser")
                .claim("role", "USER")
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSigningKey())
                .compact();

        Map<String, String> response = new LinkedHashMap<>();
        response.put("username", "demoUser");
        response.put("token", token);
        return response;
    }
}
