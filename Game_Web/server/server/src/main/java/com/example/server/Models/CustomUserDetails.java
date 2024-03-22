package com.example.server.Models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomUserDetails implements UserDetails {
    private  User user;

    public CustomUserDetails(Optional<User> user) {
        this.user = user.get();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(this.user.getRole().toString()));
//        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+ this.user.getRole().toString()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPass_word();
    }

    @Override
    public String getUsername() {
        return  this.user.getUser_name();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
