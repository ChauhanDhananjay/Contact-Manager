package com.contact.Smart.Contact.Manager.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.contact.Smart.Contact.Manager.Entity.Contacts;
import com.contact.Smart.Contact.Manager.Entity.User;

@Repository
public interface ContactRepo extends JpaRepository<Contacts, String> {
    List<Contacts> findByUser(User user);
    @Query("SELECT c FROM Contacts c WHERE c.user.id = :userId")
    List<Contacts> findByUserId(@Param("userId")String userId);
}
