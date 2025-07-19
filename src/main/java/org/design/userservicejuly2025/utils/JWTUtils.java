package org.design.userservicejuly2025.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.design.userservicejuly2025.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import io.jsonwebtoken.security.Keys;
import org.springframework.util.Assert;

@Component
public class JWTUtils {

    @Value("${jwt.secret.key}")
    private String secretKey;

    public String generateToken(User user,Date expirationDate) {
        // We need a signing key, so we'll create one just for this example. Usually
// the key would be read from your application configuration instead.

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(putClaims(user))
                .signWith(key)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .compact();
    }

    /**
     * Generates a JWT token for the given user.
     *
     * @param user the user for whom the token is generated
     * @return a JWT token as a String
     */

    private Map<String, Object> putClaims(User user) {
        return Map.of(
                "id", user.getId(),
                "email", user.getEmail(),
                "name", user.getName(),
                "roles", user.getRoles().stream().map(role -> role.getValue()).collect(Collectors.joining(","))
        );
    }

    public boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(key) // Use verifyWith to ensure the token is signed with the correct key
                    .build()
                    .parseSignedClaims(token);
            Assert.notNull(claims, "Claims cannot be null");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
