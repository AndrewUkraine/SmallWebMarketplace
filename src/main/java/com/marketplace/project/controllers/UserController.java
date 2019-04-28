package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.OfferRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepositoryDto;
import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.User;
import com.marketplace.project.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping(value = { "/registration","/update" }) //two mapping
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserRepositoryDto userRepositoryDto;

    @ModelAttribute("user")
    public Model registration(Model model) {
       return model.addAttribute("user", new UserRegistrationDto());
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    //SaveUser
    @PostMapping
    public String addNewUser(@ModelAttribute ("user") @Valid UserRegistrationDto user, BindingResult result) {

        User existingEmail = userRepository.findByEmail(user.getEmail());

        User existingPhone = userRepository.findByPhone(user.getPhone());

        if (existingEmail != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (existingPhone != null){
            result.rejectValue("phone", null, "There is already an account registered with that phone");
        }

        if (result.hasErrors()){
            return "registration";
        }

        userRepositoryDto.saveNewUser(user);

        return "redirect:/registration?success";
    }

    // Update User
    @PostMapping (value = "update")
    public String updateUser(@AuthenticationPrincipal User user, @ModelAttribute ("user") @Valid UserRegistrationDto userupdate, BindingResult results) {

     userRepository.findById(user.getId());

        user.setFirsName(userupdate.getFirsName());
        user.setSecondName(userupdate.getSecondName());
        user.setCity(userupdate.getCity());

userRepository.save(user);

        return "redirect:/registration?success";
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
