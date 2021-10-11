package com.example.demo.utils;

import com.example.demo.constant.KeyConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtility implements Serializable {

  public String generateJwtToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + KeyConstant.TOKEN_VALIDITY))
        .signWith(SignatureAlgorithm.HS512, KeyConstant.jwtSecret).compact();
  }

  public Boolean validateJwtToken(String token, UserDetails userDetails) {
    String username = getUsernameFromToken(token);
    Claims claims = Jwts.parser().setSigningKey(KeyConstant.jwtSecret).parseClaimsJws(token).getBody();
    Boolean isTokenExpired = claims.getExpiration().before(new Date());
    return (username.equals(userDetails.getUsername()) && !isTokenExpired);
  }

  public String getUsernameFromToken(String token) {
    final Claims claims = Jwts.parser().setSigningKey(KeyConstant.jwtSecret).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }
}
