package com.scm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scm.entites.Contact;

@Service
public interface ContactService {

    public Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAll();

    Contact getById(String id);

    void delete(String id);

    List<Contact> search(String name, String email, String phoneNumber);

    List<Contact> getByUserId(String id);
}
