package com.scm.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.scm.repositery.UserRepositery;

@Component
public class CoustomUserDetailsServices implements UserDetailsService {

    @Autowired
    UserRepositery userRepositery;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username  : " + username);
        return userRepositery.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found with this email id" + username));

    }

}
