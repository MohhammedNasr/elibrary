package com.project.elibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.elibrary.models.User;
import com.project.elibrary.repositories.UserRepo;

@Service
public class UserService implements UserDetailsService { // 3shan a3rf l user ele logged in fl session de
    // hpass l service class dh ll spring security fl config bta3y, l class dh
    // mas'olyeto eno hwa ele yfetch l user obj de

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepo.findByEmail(email).orNull();
        return user;
    }

}
