package com.spaceyatech.berlin.security.jwt;


import com.spaceyatech.berlin.services.usersevice.UserDetailss;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

   /**
    * generate a JWT/refresh token from username, date, expiration, secret
    * get username from JWT
    * validate a JWT
    * */
   @Value("${jwt-properties.app.jwtSecret}")
   private String jwtSecret;

    @Value("${jwt-properties.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${jwt-properties.app.refreshExpirationMs}")
   private int refreshExpirationMs;  //refreshtoken lives for 100days

    public String generateJwtToken(Authentication authentication) {

        UserDetailss userPrincipal = (UserDetailss) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) //expires after 1 day
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateJwtRefreshToken(Authentication authentication) {

        UserDetailss userPrincipal = (UserDetailss) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + refreshExpirationMs)) //expires after 10 days
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String newRefreshToken(String username) {



        return Jwts.builder()
                .setSubject((username))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + refreshExpirationMs)) //expires after 10 days
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String newAccessToken(String username) {



        return Jwts.builder()
                .setSubject((username))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) //expires after 1 day
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }



    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature:--> {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token:--> {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired:--> {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported:--> {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty:--> {}", e.getMessage());
        }

        return false;
    }


}
