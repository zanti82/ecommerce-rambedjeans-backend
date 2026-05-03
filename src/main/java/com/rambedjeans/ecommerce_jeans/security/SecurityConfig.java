package com.rambedjeans.ecommerce_jeans.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean //este es para configurar el auth
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) //esto e spara formularios 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() //solo permite  lo que tenga esto
                .requestMatchers("/api/usuarios/**").permitAll() 
                .requestMatchers("/api/tallas/**").permitAll() 
                .requestMatchers("/api/referencias/**").permitAll() 
                .requestMatchers("/api/variantes/**").permitAll() 
                .requestMatchers("/api/facturas/**").permitAll() 
                .anyRequest().authenticated() //todo lo demas requiere autenticacion
            );

            //auth/** → público ✔
            //todo lo demás → bloqueado 🔒

        return http.build();
    }

    @Bean //este para codigficar el pass
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}