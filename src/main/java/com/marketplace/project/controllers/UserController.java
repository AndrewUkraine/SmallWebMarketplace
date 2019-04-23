package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.ImageRepository;
import com.marketplace.project.dao.jpadatarepository.OfferRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.User;
import com.marketplace.project.entities.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/registration")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private ImageRepository imageRepository;

    @ModelAttribute("user")
    public Model registration(Model model) {
       return model.addAttribute("user", new User());
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    //Save/Update user
    @PostMapping
    public String addNewUser(@ModelAttribute ("user") @Valid User user, BindingResult result) {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        User existing = userRepository.findByEmail(user.getEmail());

        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()){
            return "registration";
        }


        user.setRoles(Collections.singleton(RoleType.USER));
        user.setActive(true);
        user.setMatchingPassword(passwordEncoder.encode(user.getMatchingPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/registration?success";
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

    @GetMapping("/update-user")
    public String updateUser(@AuthenticationPrincipal User user,  Model model) {
        model.addAttribute("user", user);
        return "updateUser";
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

        //get all offers and then remove all images/name
      List<Offer>  listOffers =  offerRepository.findBySeller(userRepository.findById(id));

      for (Offer offer : listOffers){
          //delete images from hardware
          for (Image image : offer.getImages()) {
              if (!image.getName().isEmpty()) {
                  File file = new File(image.getPath() + image.getName());
                  if (file.delete()) {
                      System.out.println(file.getName() + " is deleted!");
                  }
              }

          }
      }
        userRepository.deleteById(id);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/users"; //registration
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
