package com.dam_proyect.api_figth.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JWTUtil {

    private static final String SECRET = "VGhpcyBpcyBhIHNlY3JldCBrZXkgdXNlZCBmb3IganNvbg==";
    private final SecretKey secretKey;
    private final long expirationMillis = 3600000; // 1 hour

    public JWTUtil() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId) // ← Aquí guardas el ID del usuario como subject
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(secretKey)
                .compact();
    }

    public String extractUserId(String token) {
        Claims claims = parseClaims(token);
        return claims != null ? claims.getSubject() : null; // ← Esto ahora devuelve el ID
    }

    public boolean isTokenExpired(String token) {
        Claims claims = parseClaims(token);
        if (claims == null) return true;
        return claims.getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUserId(token);
        return extractedUsername != null && extractedUsername.equals(username) && !isTokenExpired(token);
    }

    // Metodo privado para parsear claims y evitar duplicar código
    private Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            // Aquí puedes loguear el error si quieres
            return null;
        }
    }
}
