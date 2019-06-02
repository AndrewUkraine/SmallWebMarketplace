package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.*;
import com.marketplace.project.entities.*;
import com.marketplace.project.services.EmailService;
import com.marketplace.project.services.ImageService;
import com.marketplace.project.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = { "/registration", "/email" }) //two mapping
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserRepositoryDto userRepositoryDto;

    @Autowired
    private EmailTokenRepository emailTokenRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private ImageService imageService;


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
    @Transactional
    public String addNewUser(@ModelAttribute ("user") @Valid UserRegistrationDto user, BindingResult result, HttpServletRequest request, @RequestParam("file") MultipartFile file) {

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

        User userForToken = userRepositoryDto.saveNewUser(user);

        try {
            imageService.saveAvatar(file, userForToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        EmailToken token = new EmailToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(userForToken);
        token.setExpiryDate(30);
        emailTokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("testwebmarketplace@gmail.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Email confirming request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", userForToken);
        model.put("signature", "https://memorynotfound.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/confirm-account?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);


        return "redirect:/login?ok";
    }



    //Get User by Id
    @GetMapping(value = "/user-{id}")
    public String getById(@PathVariable int id, Model model) {

        userRepository.findById(id).ifPresent(o -> model.addAttribute("users", o));
        return "allUsers";
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
