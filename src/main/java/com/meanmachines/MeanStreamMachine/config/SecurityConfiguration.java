package com.meanmachines.MeanStreamMachine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz.anyRequest().permitAll())
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.disable())
                .headers(header -> header.frameOptions(frameOptions -> frameOptions.disable()));
        return http.build();
    }

}