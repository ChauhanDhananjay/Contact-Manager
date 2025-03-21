package com.contact.Smart.Contact.Manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.contact.Smart.Contact.Manager.Entity.User;
import com.contact.Smart.Contact.Manager.Forms.UserForm;
import com.contact.Smart.Contact.Manager.Helpers.Message;
import com.contact.Smart.Contact.Manager.Helpers.Messagetype;
import com.contact.Smart.Contact.Manager.Service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class PageController{
     @Autowired
    private UserService userService;

     @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
     @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");
        model.addAttribute("name", "xyxzxz");
        model.addAttribute("youtube", "xyxzxz");
        // sending data to viewc
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage() {
        System.out.println("About page loading");
        return "about";
    }
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("services page loading");
        return "services";
    }
    @RequestMapping("/contact")
    public String contactPage() {
        System.out.println("contact page loading");
        return "contact";
    }
    @RequestMapping("/login")
    public String loginPage() {
        System.out.println("login page loading");
        return "login";
    }
    @RequestMapping("/register")
    public String registerPage(Model model) {
        System.out.println("register page loading");
        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);
        return "register";
    }
    @RequestMapping(value="/do-register", method = RequestMethod.POST )
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult,HttpSession session)
    {    System.out.println("Processing registration");
        System.out.println(userForm);

        if (rBindingResult.hasErrors()) {
            return "register";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(true);
        user.setProfilePic(
                "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75");

        User savedUser = userService.saveUser(user);

        System.out.println("user saved :");
        Message message = Message.builder().content("Registration Successful").type(Messagetype.green).build();

        session.setAttribute("message",message);
        return "redirect:/register";
    }

}