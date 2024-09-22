package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.scm.services.Impl.CoustomUserDetailsServices;

@Configuration
public class SecurityConfig {
    @Autowired
    CustomAuthenticationSuccessHandler handler;

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
            auth.requestMatchers("/do-logout").permitAll();
            auth.requestMatchers("/user/**").authenticated();
            auth.anyRequest().permitAll();
        });

        httpSecurity.formLogin(formLogin -> {

            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/dashboard");
            // formLogin.successHandler("user/dashboard");
            // formLogin.failureForwardUrl("/login?error=true");

            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.oauth2Login(oauth2 -> {
            oauth2.loginPage("/login");
            oauth2.defaultSuccessUrl("/user/dashboard", true);
            oauth2.successHandler(handler);
        });

        httpSecurity.logout(logout -> {
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/do-logout", "GET"));
            logout.logoutSuccessUrl("/login?logout=true");
        });

        return httpSecurity.build();
    }
}
