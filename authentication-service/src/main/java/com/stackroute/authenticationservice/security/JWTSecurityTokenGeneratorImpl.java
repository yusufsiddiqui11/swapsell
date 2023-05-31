package com.stackroute.authenticationservice.security;

import com.stackroute.authenticationservice.domain.UserLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements JWTSecurityTokenGenerator {
    @Override
    public Map<String, String> createToken(UserLogin user) {
        Map<String, String> token = new HashMap<>();

        String jwtTokenString = Jwts.builder()
                .claim("email", user.getEmail())
                .setSubject(user.getEmail())
                .signWith(SignatureAlgorithm.HS256, "secret69")
                .compact();

        token.put("token", jwtTokenString);
        token.put("message", "Login Successful");
        token.put("email", user.getEmail());

        return token;
    }
}
