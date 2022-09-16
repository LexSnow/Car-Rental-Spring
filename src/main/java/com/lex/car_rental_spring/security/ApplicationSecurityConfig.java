package com.lex.car_rental_spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.lex.car_rental_spring.security.permissions.UserPermission.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/cars/rent", "/cars/return").hasAnyAuthority(CAR_RENT.getPermission(), CAR_RETURN.getPermission())
                .antMatchers(HttpMethod.GET,"/admin/**").hasAnyAuthority(CAR_READ.getPermission(), LOCATION_READ.getPermission(), HISTORY_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(CAR_WRITE.getPermission(), LOCATION_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/admin/**").hasAnyAuthority(CAR_WRITE.getPermission(), LOCATION_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/admin/**").hasAnyAuthority(CAR_WRITE.getPermission(), LOCATION_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and().build();
    }

}
