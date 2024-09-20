package com.scm.services;

import java.util.Optional;

import com.scm.entites.User;

import java.util.*;

public interface userService {

    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updateUser(User user);

    public void deleteUser(String id);

    public boolean isUserExist(String id);

    public boolean isUserExistByEmail(String email);

    public List<User> getAllUsers();

}
