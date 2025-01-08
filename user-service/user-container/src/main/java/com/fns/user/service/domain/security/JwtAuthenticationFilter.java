package com.fns.user.service.domain.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
@Slf4j
public class JwtAuthenticationFilter extends org.springframework.web.filter.OncePerRequestFilter {


    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            log.info("Cookie, {}", cookies.length);
            String jwt = Arrays.stream(cookies)
                    .filter(cookie -> "accessToken".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);

            log.info("JWT, {}", jwt);

            if (jwt != null) {
                try {
                    Claims claims = Jwts.parser()
                            .setSigningKey(secretKey.getBytes())
                            .parseClaimsJws(jwt)
                            .getBody();

                    String username = claims.get("username", String.class);
                    log.info("Username from JWT: {}", username);

                    if (username != null) {
                        UserAuthentication authentication = new UserAuthentication(username);
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (Exception ex) {
                    log.error("JWT validation failed: {}", ex.getMessage());

                    // Set response status
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                    // Write JSON response
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    String errorMessage = String.format("{\"error\": \"Unauthorized\", \"message\": \"%s\"}", ex.getMessage());
                    response.getWriter().write(errorMessage);
                    return;
                }
            }

        }

        filterChain.doFilter(request, response);
    }

}
