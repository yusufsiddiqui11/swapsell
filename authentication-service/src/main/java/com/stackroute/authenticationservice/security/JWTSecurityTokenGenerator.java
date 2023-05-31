package com.stackroute.authenticationservice.security;

import com.stackroute.authenticationservice.domain.UserLogin;

import java.util.Map;

public interface JWTSecurityTokenGenerator {
    Map<String, String> createToken(UserLogin user);

}
