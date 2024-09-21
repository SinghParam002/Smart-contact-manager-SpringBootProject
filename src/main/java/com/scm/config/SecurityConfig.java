package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.services.Impl.CoustomUserDetailsServices;

@Configuration
public class SecurityConfig {

    @Autowired
    private CoustomUserDetailsServices coustomUserDetailsServices;

    // @Bean
    // public UserDetailsService userDetailsService() {

    // UserDetails user1 =
    // User.withDefaultPasswordEncoder().username("paramjeet").password("paramjeet@123").build();

    // var InMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1);
    // return InMemoryUserDetailsManager;
    // }

    // simple Dao authentication use Here
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // User detail service ka object:
        System.out.println(coustomUserDetailsServices);
        daoAuthenticationProvider.setUserDetailsService(coustomUserDetailsServices);
        // password encoder ka object chaiye :
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configation of Spring SecurityFilerchain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/user/**").authenticated();
            auth.anyRequest().permitAll();
        });

        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
