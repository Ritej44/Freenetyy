package com.app.authentification.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class jwtService {

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    private String generateToken(HashMap<String, Object> claims, UserDetails userDetails) {
    return buildToken(claims);
    }
}
