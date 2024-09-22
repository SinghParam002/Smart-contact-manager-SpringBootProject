package com.scm.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

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

            // Redirect to desired page after successful authentication
            new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
        } else {
            // Handle non-OAuth2 cases or fallback
            logger.warn("Authentication token is not of type OAuth2AuthenticationToken");
            new DefaultRedirectStrategy().sendRedirect(request, response, "/login?error=true");
        }
    }
}
