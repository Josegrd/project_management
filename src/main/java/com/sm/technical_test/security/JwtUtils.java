package com.sm.technical_test.security;

import java.time.Instant;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sm.technical_test.entity.UserCredential;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.sm.technical_test.model.JwtClaim;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtUtils {
    private final String appName = "Project-Management";
    private final long expirationInSecond = 86400;
    private final String secretKey = "secretKey";

    public String generateToken(UserCredential userCredential) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
            return JWT.create()
                    .withIssuer(appName)
                    .withSubject(userCredential.getId())
                    .withExpiresAt(Instant.now().plusSeconds(expirationInSecond))
                    .withIssuedAt(Instant.now())
                    .withClaim("role", userCredential.getRole().getRole().name())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            log.error("error while creating jwt token: {}", e.getMessage());
            throw new RuntimeException();
        }
    }

    public boolean verifyJwtToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getIssuer().equals(appName);
        } catch (Exception e) {
            log.error("invalid verification JWT: {}", e.getMessage());
            return false;
        }
    }

    public JwtClaim getUserInfoByToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            return JwtClaim.builder()
                    .userId(decodedJWT.getSubject())
                    .role(decodedJWT.getClaim("role").asString())
                    .build();
        } catch (JWTVerificationException e) {
            log.error("error while getting user info by token: {}", e.getMessage());
            return null;
        }
    }
}
