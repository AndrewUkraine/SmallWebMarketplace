package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.OfferRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.User;
import com.marketplace.project.entities.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
//@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registrationPage";
    }

    @GetMapping("/update-user")
    public String updateUser(@AuthenticationPrincipal User user,  Model model) {
        model.addAttribute("user", user);
        return "updateUser";
    }


    // Add new User +++
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addNewUser(@ModelAttribute User user, Map<String, Object> model) {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        if (user.getId() == null)
        {
            model.put("message", "User doesn't exists!");
        }

        user.setRoles(Collections.singleton(RoleType.USER));
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/login";

    }

    // Update User
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute User user, Map<String, Object> model) {

            userRepository.save(user);
            return "redirect:/offers";
    }


    //Get User by Id
    @GetMapping(value = "/user-{id}")
    public String getById(@PathVariable int id, Model model) {

        userRepository.findById(id).ifPresent(o -> model.addAttribute("users", o));
        return "allUsers";
    }


    //get User By Email
    @GetMapping(path = "/email/{email}")
    public @ResponseBody
    User findByEmail(@PathVariable String email, Model model) {
        User user = userRepository.findByEmail(email);
        model.addAttribute("users", userRepository.findByEmail(email));
        return user;
    }


    //get All users +++
    @GetMapping(path = "/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "allUsers";
    }

    //delete user +++
    @RequestMapping("user/delete/{id}")
    public String deleteOfferById(@PathVariable Integer id, Model model) {
        userRepository.deleteById(id);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/users";
    }

}
