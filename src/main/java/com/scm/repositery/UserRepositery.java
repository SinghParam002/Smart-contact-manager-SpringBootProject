package com.scm.repositery;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entites.User;

@Repository
public interface UserRepositery extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
}
