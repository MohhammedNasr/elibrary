package com.project.elibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthenticationProvider {

    @Autowired
    private UserService userService; // 3shan a3rf l user ele logged in delw'ty

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; // 3shan ashof lw l pass ele dkhlt hya hya ele fl db

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userdetails = this.userService.loadUserByUsername(email);

        if (userdetails == null) {
            throw new UsernameNotFoundException("Invalid Email");

        }

        if (!this.bCryptPasswordEncoder.matches(password, userdetails.getPassword())) {
            throw new UsernameNotFoundException("Invalid Email");

        }

        return new UsernamePasswordAuthenticationToken(userdetails.getUsername(), userdetails.getPassword(),
                userdetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) { // byhdd no3 l authentication ele l provider hyrg3o
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
