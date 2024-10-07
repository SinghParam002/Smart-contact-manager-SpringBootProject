package com.scm.services.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entites.Contact;
import com.scm.helper.ResourceNotFound;
import com.scm.repositery.contactRepo;
import com.scm.services.ContactService;

import lombok.var;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    contactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
        String conid = UUID.randomUUID().toString();
        contact.setId(conid);
        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {

        return null;
    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id).orElseThrow(() -> new ResourceNotFound("contact not found with id " + id));
    }

    @Override
    public void delete(String id) {

        var cc = contactRepo.findById(id).orElseThrow(() -> new ResourceNotFound("contact not found with id " + id));

        contactRepo.delete(cc);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public List<Contact> getByUserId(String id) {

        return contactRepo.findByUserId(id);
    }

}
