package com.project.elibrary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.project.elibrary.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserService userservice;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
        http.userDetailsService(userservice)
                .authorizeRequests()
                .antMatchers("/library", "/library/save-user", "/library/reset-pass").permitAll()
                .antMatchers("/library/adminHomepage").hasAuthority("Admin")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/library/login")
                .usernameParameter("email")
                .loginProcessingUrl("/library/check-user")
                .successHandler(new MyAuthenticationSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/library/logout"))
                .logoutSuccessUrl("/library/login");

        return http.build();
    }

}