package com.example.jwtvalidation.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtvalidation.dto.ValidateTokenRequest;
import com.example.jwtvalidation.security.JwtTokenValidator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    private final JwtTokenValidator validator;

    public SecurityController(JwtTokenValidator validator) {
        this.validator = validator;
    }

    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(
            @RequestBody ValidateTokenRequest request) {

        try {
            Claims claims = validator.validateToken(request.getToken());

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("valid", true);
            response.put("username", claims.getSubject());
            response.put("claims", claims);

            return ResponseEntity.ok(response);

        } catch (JwtException | IllegalArgumentException ex) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("valid", false);
            response.put("message", "Invalid or expired token");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
