package com.olivero.auth.auth_service.security;

import com.olivero.auth.auth_service.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

@Service
public class JwtTokenProvider {

    @Value("${jwt.private-key-path}")
    private Resource privateKeyResource;

    @Value("${jwt.public-key-path}")
    private Resource publicKeyResource;

    private PrivateKey privateKey;

    private PublicKey publicKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    @PostConstruct
    public void init() throws Exception {
        this.privateKey = loadPrivateKey();
        this.publicKey = loadPublicKey();
    }

    public String generateAccessToken(User user, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("token_type", "access");
        claims.put("roles", roles);
        claims.put("email", user.getEmail());

        return buildToken(user, claims, jwtExpiration);
    }

    public String generateRefreshToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("token_type", "refresh");
        return buildToken(user, claims, refreshExpiration);
    }

    private String buildToken(User user, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .claims(claims)
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }

    public boolean isAccessTokenValid(String token, User user) {
        try {
            Claims claims = extractClaims(token);

            String type = claims.get("token_type", String.class);
            if (!"access".equals(type)) {
                return false;
            }

            boolean userMatches = claims.getSubject().equals(user.getId().toString());
            boolean notExpired = !claims.getExpiration().before(new Date());

            return userMatches && notExpired;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRefreshTokenValid(String token, User user) {
        try {
            Claims claims = extractClaims(token);

            String type = claims.get("token_type", String.class);
            if (!"refresh".equals(type)) {
                return false;
            }

            boolean userMatches = claims.getSubject().equals(user.getId().toString());
            boolean notExpired = !claims.getExpiration().before(new Date());

            return userMatches && notExpired;

        } catch (Exception e) {
            return false;
        }
    }

     //Mejorar para validar si esta expirado (excepcion)
     public Claims extractClaims(String token) {
         return Jwts.parser()
                 .verifyWith(publicKey)
                 .build()
                 .parseSignedClaims(token)
                 .getPayload();
     }

    private PrivateKey loadPrivateKey() throws Exception {
        byte[] keyBytes = privateKeyResource.getInputStream().readAllBytes();
        String key = new String(keyBytes, StandardCharsets.UTF_8);

        String pem = key
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("\n", "")
                .replace("\r", "")
                .trim(); // 👈 importante

        byte[] encoded = Base64.getDecoder().decode(pem);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encoded));
    }

    private PublicKey loadPublicKey() throws Exception {
        byte[] keyBytes = publicKeyResource.getInputStream().readAllBytes();
        String key = new String(keyBytes, StandardCharsets.UTF_8);

        String pem = key
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replace("\n", "")
                .replace("\r", "")
                .trim();

        byte[] encoded = Base64.getDecoder().decode(pem);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(encoded));
    }

}