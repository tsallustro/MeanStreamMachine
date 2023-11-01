package com.meanmachines.MeanStreamMachine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz.anyRequest().hasRole("USER"))
                .formLogin(login -> login.loginPage("/login").permitAll())
                .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout"))
                .csrf((csrf) -> csrf.disable());

        return http.build();
    }

    @Bean("userManager")
    @Profile("!h2")
    UserDetailsManager users(DataSource dataSource) {
        log.debug("Initializing live user manager...");
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        log.debug("Successfully initialized user manager");
        return users;
    }

    @Bean("userManager")
    @Profile("h2")
    UserDetailsManager usersH2(DataSource dataSource) {
        log.debug("Initializing h2 user manager...");
        UserDetails user = User.builder().username("user").password("{bcrypt}$2y$10$4/t2i8BLeq8A3UesUmNBSu5v8UGBwzckp9xng80Q88dboFON/njEm").roles("USER").build();
        UserDetails admin = User.builder().username("admin").password("{bcrypt}$2y$10$OjQY.QoeZnc1j/PXOwNbJ.4EApbRjur.mduAN/ZHhPT7CNVN1zeQW").roles("USER", "ADMIN").build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user);
        users.createUser(admin);
        log.debug("Successfully initialized user manager");
        return users;
    }
}