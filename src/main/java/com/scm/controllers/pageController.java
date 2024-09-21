package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.entites.User;
import com.scm.foms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.messageType;
import com.scm.repositery.UserRepositery;
import com.scm.services.userService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class pageController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private userService userService;

    @Autowired
    UserRepositery userRepositery;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String test() {
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/services")
    public String Services() {
        return "services";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String signup(Model model) {

        UserForm userForm = new UserForm();

        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping("/do_register")
    public String processRegsiter(
            @Valid @ModelAttribute UserForm userForm,
            BindingResult bindingResult,
            HttpSession session) {

        System.out.println(userForm);

        if (bindingResult.hasErrors()) {

            return "register";
        }

        User user2 = new User();
        user2.setName(userForm.getName());
        user2.setEmail(userForm.getEmail());
        // user2.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user2.setPassword(userForm.getPassword());
        user2.setAbout(userForm.getAbout());
        user2.setPhoneNumber(userForm.getPhoneNumber());
        user2.setProfilePic(
                "https://www.freepik.com/free-photo/young-adult-enjoying-virtual-date_23988464.htm#query=man%20profile%20pic&position=0&from_view=keyword&track=ais_hybrid&uuid=6a51801e-6cf1-4c72-88f1-c661cc9d7f44\"");
        User user3 = userService.saveUser(user2);

        Message message = Message.builder()
                .content("Register succesfull").type(messageType.green).build();
        session.setAttribute("message", message);

        System.out.println("USER SAVED " + user3.toString());
        return "redirect:/register";
    }
}
