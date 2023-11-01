package com.meanmachines.MeanStreamMachine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz.anyRequest().hasRole("USER"))
                .formLogin(login -> login.loginPage("/login").permitAll())
                .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout"))
                .csrf((csrf) -> csrf.disable());

        return http.build();
    }

    @Bean
    UserDetailsManager users(DataSource dataSource) {
        UserDetails user = User.builder().username("user").password("{bcrypt}$2y$10$4/t2i8BLeq8A3UesUmNBSu5v8UGBwzckp9xng80Q88dboFON/njEm").roles("USER").build();
        UserDetails admin = User.builder().username("admin").password("{bcrypt}$2y$10$OjQY.QoeZnc1j/PXOwNbJ.4EApbRjur.mduAN/ZHhPT7CNVN1zeQW").roles("USER", "ADMIN").build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user);
        users.createUser(admin);
        return users;
    }

}