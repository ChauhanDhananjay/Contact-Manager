package com.contact.Smart.Contact.Manager.Service;

import com.contact.Smart.Contact.Manager.Entity.Contacts;
import com.contact.Smart.Contact.Manager.Entity.User;

import java.util.List;

public interface ContactService {
    Contacts save(Contacts contact);
    Contacts update(Contacts contact);
    List<Contacts> getAll();
    Contacts getById(String id);
    void delete(String id);
    List<Contacts> search(String name,String email,String phoneNumber);
    List<Contacts> getByUserId(String userId);
    List<Contacts> getByUser(User user);
}
