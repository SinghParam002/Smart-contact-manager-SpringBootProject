package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.entites.User;
import com.scm.foms.UserForm;
import com.scm.repositery.UserRepositery;
import com.scm.services.userService;

@Controller
public class pageController {

    @Autowired
    private userService userService;

    @Autowired
    UserRepositery userRepositery;

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
        userForm.setName("paramjeet singh");
        userForm.setEmail("param@gmail.com");
        userForm.setPassword("param");
        userForm.setPhoneNumber("8923859863");
        userForm.setAbout("Mca Student");
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping("/do_register")
    public String processRegsiter(@ModelAttribute UserForm userForm) {

        User user = User.builder()
                .name(userForm.getName())
                .PhoneNumber(userForm.getPhoneNumber())
                .about(userForm.getAbout())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .profilePic(
                        "https://www.freepik.com/free-photo/young-adult-enjoying-virtual-date_23988464.htm#query=man%20profile%20pic&position=0&from_view=keyword&track=ais_hybrid&uuid=6a51801e-6cf1-4c72-88f1-c661cc9d7f44")
                .build();

        User user2 = userService.saveUser(user);

        System.out.println("USER SAVED " + user2);
        return "redirect:/register";
    }
}

// package com.scm.services.impl;

// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.scm.entites.User;
// import com.scm.helper.ResourceNotFound;
// import com.scm.repositery.UserRepositery;
// import com.scm.servicess.userService;

// @Service
// public class UserServiceImpl implements userService {

// @Autowired
// private UserRepositery userRepositery;

// @Override
// public User saveUser(User user) {

// String uuid = UUID.randomUUID().toString();
// user.setUserId(uuid);
// return userRepositery.save(user);
// }

// @Override
// public Optional<User> getUserById(String id) {

// return userRepositery.findById(id);
// }

// @Override
// public Optional<User> updateUser(User user) {

// User user2 = userRepositery.findById(user.getUserId())
// .orElseThrow(() -> new ResourceNotFound("User not found")); // Ensure correct
// exception

// // Update user details
// user2.setName(user.getName());
// user2.setPhoneNumber(user.getPhoneNumber());
// user2.setAbout(user.getAbout());
// user2.setEmail(user.getEmail());
// user2.setPassword(user.getPassword());
// user2.setProfilePic(user.getProfilePic());
// user2.setEnabled(user.isEnabled());
// user2.setEmailVerified(user.isEmailVerified());
// user2.setProvider(user.getProvider());
// user2.setProviderUserId(user.getProviderUserId());
// // Save updated user
// User savedUser = userRepositery.save(user2);

// // Return the updated user wrapped in Optional
// return Optional.ofNullable(savedUser);

// }

// @Override
// public void deleteUser(String id) {

// User user2 = userRepositery.findById(id)
// .orElseThrow(() -> new ResourceNotFound("User not found")); // Ensure correct
// exception

// userRepositery.delete(user2);
// }

// @Override
// public boolean isUserExist(String id) {

// User user2 = userRepositery.findById(id)
// .orElseThrow(() -> new ResourceNotFound("User not found")); // Ensure correct
// exception

// return user2 != null ? true : false;

// }

// @Override
// public boolean isUserExistByEmail(String email) {

// User user2 = userRepositery.findByEmail(email).orElse(null);
// return user2 != null ? true : false;
// }

// @Override
// public List<User> getAllUsers() {

// return userRepositery.findAll();

// }

// }
