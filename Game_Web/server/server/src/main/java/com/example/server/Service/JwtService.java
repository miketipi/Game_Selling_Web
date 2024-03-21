package com.example.server.Service;

import com.example.server.Models.CustomUserDetails;
import com.example.server.Models.User;
import com.example.server.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service

@AllArgsConstructor
public class JwtService {
    public final UserRepository user;
    //Use a secretkey to create jwt token

    private final String SECRET_KEY = "9867259dac9e0ff6706fa3726339775e73eab77bbfc123df6f8dd4545a2f1d62";
    private final long EXPIRATION = 1000 * 60 * 60 * 24;

    public String extractUserName(String jwt){
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaim(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    public Key getSignInKey(){
        byte [] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte); //mac dinh secret key nay dang o dang base 64 nen phai decode lai.
    }

    public String generateToken(CustomUserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(Map<String,Object> extraClaims, CustomUserDetails userDetails){
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION);
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) //Config dc Subject chinh va de xuat nhung thuoc tinh chinh cua jwt
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)//sign secret key và thuật toán (HS256 do secret key là 256bit)
                .compact();
    }
    public boolean isValidateToken(String token){
        String userEmail= extractUsername(token);
        Optional<User> userDetails = user.findByName(userEmail);
        if(userDetails.isEmpty() || isTokenExpired(token)){
            return false;
        }
        return true;
    }

    private String extractUsername(String token) {
        return extractAllClaim(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        if(extractExpiration(token).before(new Date())){
            return true;
        }
        return false;
    }


        public Date extractExpiration(String token){
            return extractAllClaim(token).getExpiration();
        }

}
