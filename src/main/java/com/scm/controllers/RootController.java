package com.scm.controllers;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.entites.User;
import com.scm.helper.Helper;
import com.scm.services.userService;

@ControllerAdvice
public class RootController {
    @Autowired
    private userService uService;
    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @ModelAttribute
    public void addLoggedUserInformation(Model model, Authentication authentication) {

        if (authentication == null) {
            return;
        }

        String Username = Helper.getLoginEmail(authentication);
        logger.info("User name : " + Username);
        User user = uService.getuserByemail(Username);

        System.out.println(user.getEmail());
        System.out.println(user.getName());
        model.addAttribute("loginUser", user);
    }

}
