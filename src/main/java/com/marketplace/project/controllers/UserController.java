package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.ImageRepository;
import com.marketplace.project.dao.jpadatarepository.OfferRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.User;
import com.marketplace.project.services.OfferService;
import com.marketplace.project.services.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Add new User
    @GetMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String city, @RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam String phone, @RequestParam String last_name) {

        User user = new User();
        user.setCity(city);
        user.setEmail(email);
        user.setFirsName(name);
        user.setPassword(password);
        user.setPhone(phone);
        user.setSecondName(last_name);

        userRepository.save(user);
        return "Saved";
    }

    //Update User
    @GetMapping(path = "/upd/{id}")
    public @ResponseBody
    User updUser(@RequestParam String city, @RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam String phone, @RequestParam String last_name, @PathVariable Integer id) {

        User u = userRepository.getOne(id);
        u.setCity(city);
        u.setEmail(email);
        u.setFirsName(name);
        u.setPassword(password);
        u.setPhone(phone);
        u.setSecondName(last_name);

        return userRepository.save(u);
    }


    //Get User by Id
    @GetMapping(value = "/user-{id}")
    public String getById(@PathVariable int id, Model model) {

        userRepository.findById(id).ifPresent(o -> model.addAttribute("users", o));
        return "allUsers";
    }


    //delet User By Id
    @GetMapping(path = "/delete/{id}")
    public @ResponseBody
    String deleteById(@PathVariable Integer id) {
        userRepository.deleteById(id);

        return "User is deleted";
    }

    //get User By Email
    @GetMapping(path = "/email/{email}")
    public @ResponseBody
    User findByEmail(@PathVariable String email, Model model) {
        User user = userRepository.findUserByEmail(email);
        model.addAttribute("users", userRepository.findUserByEmail(email));
        return user;
    }


    //get All users
    @GetMapping(path = "/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "allUsers";
    }

}
