package com.fns.user.service.domain.util;

import com.fns.user.service.domain.entity.Location;
import com.fns.user.service.domain.entity.Role;
import com.fns.user.service.domain.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class TokenUtil {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24 hours in milliseconds

    public String generateToken(User user, Role role) {

        // Use the secret key directly without decoding
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("username", user.getUser_name())
                .claim("email", user.getEmail())
                .claim("role", role.getRole_name())
                .claim("profilePicture", user.getProfile_picture())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }
}
