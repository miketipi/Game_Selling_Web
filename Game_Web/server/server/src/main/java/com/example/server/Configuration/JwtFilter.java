package com.example.server.Configuration;

import com.example.server.Models.CustomUserDetails;
import com.example.server.Service.CustomUserDetailsService;
import com.example.server.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter  extends OncePerRequestFilter {
    private final JwtService jwtService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected  void doFilterInternal(@NonNull HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull FilterChain filterChain) throws ServletException, IOException{
        //Cu moi yeu cau HTTP se trai qua filter nay
        //Lay trong header phan tu Value cua key Authorization
        final String header = req.getHeader("Authorization");
        //String jwt
        final String jwt;
        final String username;
        //Neu khong co header dung hoac khong phai bearer token, skip
        if(header == null || !header.startsWith("Bearer ")){
            //Skip va cho qua buoc tiep theo cua chuoi security filter chain
            filterChain.doFilter(req, res);
            return;
        }
        //Lay chuoi jwt
        jwt = header.substring(7);
        //extract tu jwt
        username = jwtService.extractUserName(jwt);
        //Truong hop da tim ra nguoi dung va cbi security context holder chua co gia tri
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            CustomUserDetails customUserDetails = (CustomUserDetails) this.customUserDetailsService.loadUserByUsername(username);
            if(jwtService.isValidateToken(jwt)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customUserDetails,null, customUserDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                System.out.println(authenticationToken);
            }
        }
        filterChain.doFilter(req,res);
    }
}
