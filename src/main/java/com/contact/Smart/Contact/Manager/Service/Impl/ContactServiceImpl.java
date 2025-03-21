
package com.contact.Smart.Contact.Manager.Service.Impl;

import java.util.List;
import java.util.UUID;

import com.contact.Smart.Contact.Manager.Entity.User;
import com.contact.Smart.Contact.Manager.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contact.Smart.Contact.Manager.Entity.Contacts;
import com.contact.Smart.Contact.Manager.Helpers.ResourceNotFoundException;
import com.contact.Smart.Contact.Manager.Repository.ContactRepo;


@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contacts save(Contacts contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public Contacts update(Contacts contact) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Contacts> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public void delete(String id) {
       var contact = contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact not found with given id"+id));;
        contactRepo.delete(contact);
    }

    @Override
    public List<Contacts> search(String name, String email, String phoneNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Contacts> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);
        
    }

    @Override
    public List<Contacts> getByUser(User user) {
        return contactRepo.findByUser(user);
    }

    @Override
    public Contacts getById(String id) {
        return contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact not found with given id"+id));
    }

}
