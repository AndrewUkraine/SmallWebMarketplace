package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.ImageRepository;
import com.marketplace.project.dao.jpadatarepository.OfferRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.User;
import com.marketplace.project.entities.enums.RoleType;
import com.marketplace.project.services.OfferService;
import com.marketplace.project.services.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.math.BigDecimal;
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

    // Add new User +++
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addNewUser(@ModelAttribute User user,  Map<String, Object> model) {

//        User userFromDb = userRepository.findByEmail(user.getEmail());
//
//        if (userFromDb != null) {
//           // model.put("message", "User exists!");
//            return "offers";
//        }


        user.setRoles(Collections.singleton(RoleType.USER));
        user.setActive(true);
        //user.setUserRoles(us);
        userRepository.save(user);

        return "redirect:/login";

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
