package com.example.back_end.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.back_end.model.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenConfig {
    //passar a variavel para application properties
    private String secret="teste";

    public String generateToken(UserEntity userEntity){

        Algorithm algorithm= Algorithm.HMAC384(secret);
        return JWT.create().withClaim("User id", userEntity.getId()).
                withSubject(userEntity.getEmailAddress()).
                withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now()).sign(algorithm);
    }
}