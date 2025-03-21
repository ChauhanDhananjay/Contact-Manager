package com.contact.Smart.Contact.Manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contact.Smart.Contact.Manager.Service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //User DashBoard
    @RequestMapping(value="/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    @RequestMapping(value="/profile")
    public String userProfile(Model model, Authentication authentication)
    {

        return "user/profile";
    }
    //User add contact
    //User view
    //user edit
    //user delete contact
}
