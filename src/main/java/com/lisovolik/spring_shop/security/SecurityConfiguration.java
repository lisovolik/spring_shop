package com.lisovolik.spring_shop.security;

import com.lisovolik.spring_shop.security.jwt.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
    //private final CustomUserDetailService userDetailService;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf->{
                    csrf.disable();
                })
                .authorizeHttpRequests(authorize ->{
                    //authorize.requestMatchers(HttpMethod.POST,"/product").hasRole("ADMIN");
                    //authorize.requestMatchers(HttpMethod.PUT,"/product").hasRole("ADMIN");
                    //authorize.requestMatchers(HttpMethod.DELETE,"/product").hasRole("ADMIN");
                    authorize.requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll();
                    authorize.requestMatchers("/register/hello").permitAll();
                    authorize.requestMatchers("/login").permitAll();
                    authorize.requestMatchers("/register").permitAll();

                    authorize.anyRequest().authenticated();
                   // authorize.anyRequest().permitAll();
                })
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }
}
