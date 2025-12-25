package com.example.demo.security;

import com.example.demo.model.UserAccount;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // ✅ Base64 encoded secret (MANDATORY for tests)
    private final String jwtSecret =
            Base64.getEncoder().encodeToString(
                    "test-secret-key-for-jwt-provider".getBytes()
            );

    private final long EXPIRATION = 3600000;

    // ✅ MUST generate token using UserAccount
    public String generateToken(UserAccount user) {

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId())) // fallback
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .claim("userId", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    // ✅ REQUIRED by tests
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ REQUIRED by tests
    public String getEmail(String token) {
        return getClaims(token).get("email", String.class);
    }

    // ✅ REQUIRED by tests
    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    // ✅ REQUIRED by tests (fallback to subject)
    public Long getUserId(String token) {
        Claims claims = getClaims(token);

        if (claims.get("userId") != null) {
            return claims.get("userId", Long.class);
        }

        return Long.parseLong(claims.getSubject());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}
