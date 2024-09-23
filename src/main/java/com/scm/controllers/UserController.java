package com.scm.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.scm.entites.User;
import com.scm.helper.Helper;
import com.scm.services.userService;

// import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);

    @Autowired
    private userService uService;

    @ModelAttribute
    public void addLoggedUserInformation(Model model, Authentication authentication) {
        String Username = Helper.getLoginEmail(authentication);
        logger.info("User name : " + Username);
        User user = uService.getuserByemail(Username);
        System.out.println(user.getEmail());
        System.out.println(user.getName());
        model.addAttribute("loginUser", user);
    }

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {

        System.out.println("user Dashboard");

        return "user/dashboard";
    }

    @RequestMapping(value = "/profile")
    public String userProfile(Authentication authentication) {

        return "user/profile";
    }

}
