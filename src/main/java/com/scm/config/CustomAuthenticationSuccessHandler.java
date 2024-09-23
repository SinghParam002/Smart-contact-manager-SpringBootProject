package com.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.entites.Providers;
import com.scm.entites.User;
import com.scm.helper.AppConstant;
import com.scm.repositery.UserRepositery;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserRepositery repositery;
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        logger.info("Authentication success handler invoked");

        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
            String clientId = oauth2Token.getAuthorizedClientRegistrationId();
            DefaultOAuth2User user = (DefaultOAuth2User) oauth2Token.getPrincipal();

            // Log user attributes
            user.getAttributes().forEach((key, value) -> {
                logger.info("OAuth2 User Attribute - {} : {}", key, value);
            });

            logger.info("OAuth2 Client ID: {}", clientId);

            User newUser = new User();
            newUser.setUserId(UUID.randomUUID().toString());
            newUser.setRolesList(List.of(AppConstant.ROLE_USER));
            newUser.setEmailVerified(true);
            newUser.setEnabled(true);
            newUser.setPassword("suss");

            String email = user.getAttribute("email") != null ? user.getAttribute("email").toString()
                    : user.getAttribute("login").toString() + "@gmail.com";
            String picture = user.getAttribute("avatar_url").toString();
            String name = user.getAttribute("login").toString();
            String providerUserId = user.getName();

            newUser.setEmail(email);
            newUser.setProfilePic(picture);
            newUser.setName(name);
            newUser.setProviderUserId(providerUserId);
            newUser.setProvider(Providers.GITHUB);
            User user2 = repositery.findByEmail(newUser.getEmail()).orElse(null);
            if (user2 == null) {
                repositery.save(newUser);
            }
            // Redirect to desired page after successful authentication
            new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
        } else {
            // Handle non-OAuth2 cases or fallback
            logger.warn("Authentication token is not of type OAuth2AuthenticationToken");
            new DefaultRedirectStrategy().sendRedirect(request, response, "/login?error=true");
        }
    }
}
