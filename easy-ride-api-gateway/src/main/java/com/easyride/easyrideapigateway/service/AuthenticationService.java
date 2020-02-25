package com.easyride.easyrideapigateway.service;

import java.util.Collections;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Service
public class AuthenticationService {

    static String SIGNINGKEY;

    @Value("${SIGNING_KEY}")
    public void setSigningkey(String signingkey) {
        SIGNINGKEY = signingkey;
    }

    // Authentication service for validating the jwt token
    public static Authentication getAuthentication(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("EZ_R")) {
                    String user;
                    try {
                        user = Jwts.parser().setSigningKey(SIGNINGKEY).parseClaimsJws(cookie.getValue()).getBody()
                                .get("em", String.class);

                    } catch (ExpiredJwtException exception) {
                        return null;
                    }
                    if (user != null) {
                        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                    }
                }
            }
        }
        return null;
    }

}
