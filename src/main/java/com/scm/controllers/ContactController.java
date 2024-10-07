package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entites.Contact;
import com.scm.entites.User;
import com.scm.foms.contactForm;
import com.scm.helper.Helper;
import com.scm.helper.Message;
import com.scm.helper.messageType;
import com.scm.services.ContactService;
import com.scm.services.userService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @Autowired
    userService uService;

    @RequestMapping("/add")
    public String AddContact(Model model) {

        contactForm form = new contactForm();

        model.addAttribute("contactForm", form);
        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute contactForm contactForm, BindingResult bindingResult,
            Authentication authentication,
            HttpSession session) {

        if (bindingResult.hasErrors()) {
            session.setAttribute("message",
                    Message.builder().content("please correct the following errors ").type(messageType.red).build());
            return "user/add_contact";
        }

        String username = Helper.getLoginEmail(authentication);
        User user = uService.getuserByemail(username);

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setDiscription(contactForm.getDiscription());
        contact.setAddress(contactForm.getAddress());
        contact.setWebSiteLink(contactForm.getWebSiteLink());
        contact.setFavorite(contactForm.isFavorite());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setUser(user);

        contactService.save(contact);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(contactForm.toString());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        session.setAttribute("message",

                Message.builder().content("contact added successfully").type(messageType.green).build());
        return "redirect:/user/contacts/add";
    }

}
