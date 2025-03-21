package com.contact.Smart.Contact.Manager.Controller;

import com.contact.Smart.Contact.Manager.Service.ImageService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.contact.Smart.Contact.Manager.Entity.Contacts;
import com.contact.Smart.Contact.Manager.Entity.User;
import com.contact.Smart.Contact.Manager.Forms.ContactForm;
import com.contact.Smart.Contact.Manager.Helpers.Helper;
import com.contact.Smart.Contact.Manager.Helpers.Message;
import com.contact.Smart.Contact.Manager.Helpers.Messagetype;
import com.contact.Smart.Contact.Manager.Service.ContactService;
import com.contact.Smart.Contact.Manager.Service.UserService;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;
    
    @RequestMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String saveContact(@ModelAttribute ContactForm contactForm,Authentication authentication,BindingResult result,HttpSession session){
        String username = Helper.getEmailOfLoggedUser(authentication);
        // form ---> contact
        if (result.hasErrors()) {

            result.getAllErrors().forEach(error -> logger.info(error.toString()));

            session.setAttribute("message", Message.builder()
                    .content("Please correct the following errors")
                    .type(Messagetype.red)
                    .build());
            return "user/add_contact";
        }
        String filename = UUID.randomUUID().toString();
        User user = userService.getUserByEmail(username);
        String fileUrl = imageService.uploadImage(contactForm.getContactImage(),filename);
        Contacts contact = new Contacts();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileUrl);
        contact.setCloudinaryImagePublicId(filename);
        //contactService.save(contact);

        session.setAttribute("message",
                Message.builder()
                        .content("You have successfully added a new contact")
                        .type(Messagetype.green)
                        .build());
        return "redirect:/user/contacts/add";
    }

    @RequestMapping()
    public String viewContact(Authentication authentication,Model model){
        String username =Helper.getEmailOfLoggedUser(authentication);
        User user = userService.getUserByEmail(username);
        List<Contacts> contacts = contactService.getByUser(user);
        model.addAttribute("contacts", contacts);
        return "user/contacts";
    }
}
