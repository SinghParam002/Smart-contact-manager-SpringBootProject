package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {

        System.out.println("user Dashboard");

        return "user/dashboard";
    }

    @RequestMapping(value = "/profile")
    public String userProfile() {

        System.out.println("user profile");

        return "user/profile";
    }

}
