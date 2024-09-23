package com.scm.helper;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.val;

public class Helper {
    public static String getLoginEmail(Authentication authentication) {

        // AuthenticationPrincipal principal = (AuthenticationPrincipal)
        // authentication.getPrincipal();

        if (authentication instanceof OAuth2AuthenticationToken) {

            var OAuth2 = (OAuth2AuthenticationToken) authentication;
            val id = OAuth2.getAuthorizedClientRegistrationId();

            var oauth2USer = (OAuth2User) authentication.getPrincipal();
            String userName = "";
            if (id.equalsIgnoreCase("github")) {

                System.out.println("guthub user getting ");
                userName = oauth2USer.getAttribute("email") != null ? oauth2USer.getAttribute("email").toString()
                        : oauth2USer.getAttribute("login").toString() + "@gmail.com";

            }

            return userName;

        } else {
            System.out.println("getting data from user : ");
            return authentication.getName();
        }

    }

}
