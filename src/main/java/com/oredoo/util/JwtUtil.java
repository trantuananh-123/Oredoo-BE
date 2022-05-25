package com.oredoo.util;

import com.oredoo.model.UserDetail;
import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger LOGGER = LogManager.getLogger(JwtUtil.class);

    @Value("${oredoo.jwtSecret}")
    private String jwtSecret;

    @Value("${oredoo.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        return Jwts.builder()
            .setSubject((userDetail.getUsername()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public LocalDateTime getExpirationTimeFromJwtToken(String token) {
        Date date = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration();
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
