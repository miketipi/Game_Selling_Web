package com.example.server.Configuration;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.logging.Logger;

//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = false, jsr250Enabled = false)
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    public AuthenticationProvider authenticationProvider;
    @Autowired
    public JwtFilter jwtFilter;
    private static final Logger logger = Logger.getLogger(SecurityConfig.class.getName());

    //Se thu y tuong tach tung .authorizeHttpRequests ra
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(a -> a.disable())
                .authorizeHttpRequests(a -> a
                        .requestMatchers(HttpMethod.GET, "/game/**", "/gametype/**", "/publisher/**", "/platform/**", "/user/{id}")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/authenticate/signup", "/authenticate/login", "/checkout/checkout")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/cart/add", "/cart/delete", "/fav/add", "/fav/delete")
                        //.permitAll()
                        .authenticated()
                        .requestMatchers(HttpMethod.GET, "/user/all", "/cart/me", "/fav/me", "user/me", "cart/all")
                        .authenticated())
                .authenticationProvider(authenticationProvider)
                //Loi hom kia khong len la do chua setting http basic default nen la cho du co setting nhu the nao spring security van xem no la mot request khong hop le
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
                .sessionManagement(a -> a
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/game/**").allowedOrigins("*");
                registry.addMapping("/user/**").allowedOrigins("*");
            }
        };
    }
}
