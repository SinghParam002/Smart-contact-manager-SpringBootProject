package com.scm.services.Impl;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entites.User;
import com.scm.helper.AppConstant;
import com.scm.helper.ResourceNotFound;
import com.scm.repositery.UserRepositery;
import com.scm.services.userService;

@Service
public class UserServiceImple implements userService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepositery userRepositery;

    @Override
    public User saveUser(User user) {

        String uuid = UUID.randomUUID().toString();
        user.setUserId(uuid);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRolesList(List.of(AppConstant.ROLE_USER));
        return userRepositery.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {

        return userRepositery.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {

        User user2 = userRepositery.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFound("User not found")); // Ensure correct exception

        // Update user details
        user2.setName(user.getName());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setAbout(user.getAbout());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());
        // Save updated user
        User savedUser = userRepositery.save(user2);

        // Return the updated user wrapped in Optional
        return Optional.ofNullable(savedUser);

    }

    @Override
    public void deleteUser(String id) {

        User user2 = userRepositery.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found")); // Ensure correct exception

        userRepositery.delete(user2);
    }

    @Override
    public boolean isUserExist(String id) {

        User user2 = userRepositery.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found")); // Ensure correct exception

        return user2 != null ? true : false;

    }

    @Override
    public boolean isUserExistByEmail(String email) {

        User user2 = userRepositery.findByEmail(email).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {

        return userRepositery.findAll();

    }

    @Override
    public User getuserByemail(String email) {

        return userRepositery.findByEmail(email).orElse(null);
    }
}
