package com.aditya.ordermanagementsystem.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;


@Component
public class JwtUtility {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getJwtFromHeader (HttpServletRequest req){
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            System.out.println("Passed correct Bearer token :"+ bearerToken);
            return bearerToken.substring(7);
        }
        System.out.println("Bearer token is null or doesn't start with 'Bearer '");
        return null;
    }


    public String generateTokenFromUsername(UserDetails userDetails){
        String username = userDetails.getUsername();
        String jwt =  Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(key())
                .compact();
        System.out.println("Token Generated Successfully "+jwt);
        return jwt;
    }

    private Key key(){
        Key key =  Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        System.out.println(key);
        return key;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        System.out.println("Validating token: " + token);
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
            System.out.println("JWT Token Validated Successfully with token : "+ token);
            return true;
        } catch (SignatureException e) {
            // Log the exception and return false if the signature is invalid
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (Exception e) {
            // Log other exceptions like expired token, etc.
            System.out.println("Invalid JWT token: " + e.getMessage());
        }
        return false;
    }
}
