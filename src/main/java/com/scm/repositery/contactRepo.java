package com.scm.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scm.entites.Contact;
import com.scm.entites.User;

@Repository
public interface contactRepo extends JpaRepository<Contact, String> {

    List<Contact> findByUser(User user);

    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId") // Adjusted entity name and field case
    List<Contact> findByUserId(String userId); // Assuming user ID is of type Long

}
