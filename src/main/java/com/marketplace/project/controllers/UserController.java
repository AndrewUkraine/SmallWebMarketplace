package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.*;
import com.marketplace.project.entities.*;
import com.marketplace.project.services.EmailService;
import com.marketplace.project.web.dto.UserRegistrationDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping(value = { "/registration","/update", "/email" }) //two mapping
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
    public String addNewUser(@ModelAttribute ("user") @Valid UserRegistrationDto user, BindingResult result, HttpServletRequest request) {

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


        return "redirect:/logout";
    }

    // Update User
    @PostMapping (value = "update")
    public String updateUser(@AuthenticationPrincipal User user, @ModelAttribute ("user") @Valid UserRegistrationDto userupdate, BindingResult results) {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        userRepository.findById(user.getId());

        User existingPhone = userRepository.findByPhone(userupdate.getPhone());

        if (existingPhone!=null && !existingPhone.getId().equals(user.getId()))
        {
            results.rejectValue("phone", null, "You can't use this phone. There is already an account registered with that phone");
            return "redirect:/updateUser";
        }


        if (!userupdate.getUpdateNewPassword().isEmpty())
        {

            if (!userupdate.getUpdateNewPassword().equals(userupdate.getMatchingPassword()))
            {
                results.rejectValue("updateNewPassword", null, "The password fields must match");
                results.rejectValue("matchingPassword", null, "The password fields must match");
                return "updateUser";
            }

            if (passwordEncoder.matches(userupdate.getPassword(), user.getPassword()))
            {
               user.setPassword(passwordEncoder.encode(userupdate.getUpdateNewPassword()));
            }

            else {
                results.rejectValue("password", null, "You must confirm current password");
                return "updateUser";
            }
        }

        if (userupdate.getPhone().isEmpty()) {
            results.hasErrors();
            return "updateUser";
        }

        if (userupdate.getFirsName().isEmpty()) {
            results.hasErrors();
            return "updateUser";
        }


        if (userupdate.getSecondName().isEmpty()){
            results.hasErrors();
            return "updateUser";
        }


        if(userupdate.getCity().isEmpty()){
            results.hasErrors();
            return "updateUser";
        }

        user.setPhone(userupdate.getPhone());
        user.setFirsName(userupdate.getFirsName());
        user.setSecondName(userupdate.getSecondName());
        user.setCity(userupdate.getCity());
        userRepository.save(user);

        return "redirect:/registration/update-user?success";
    }

    //Get User by Id
    @GetMapping(value = "/user-{id}")
    public String getById(@PathVariable int id, Model model) {

        userRepository.findById(id).ifPresent(o -> model.addAttribute("users", o));
        return "allUsers";
    }

    //Get Email
    @GetMapping(value = "/email")
    public String getEmail(@AuthenticationPrincipal User user, EmailToken emailToken, Model model) {

        model.addAttribute("useremail", user);
        model.addAttribute("emailtoken", emailToken);
        return "updateEmail";
    }

    @GetMapping("/update-user")
    public String updateUser(@AuthenticationPrincipal User user, @ModelAttribute UserRegistrationDto userRegistrationDto,  Model model) {

        userRegistrationDto.setFirsName(user.getFirsName());
        userRegistrationDto.setSecondName(user.getSecondName());
        userRegistrationDto.setCity(user.getCity());
        userRegistrationDto.setPassword(user.getPassword());
        userRegistrationDto.setEmail(user.getEmail());
        userRegistrationDto.setPhone(user.getPhone());


        model.addAttribute("user", userRegistrationDto);
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


    //UpdateEmail
    @PostMapping (value = "email")
    public String updateEmail(@AuthenticationPrincipal User user, @ModelAttribute ("token") @Valid EmailToken emailToken, BindingResult result, HttpServletRequest request) {

        User existingEmail = userRepository.findByEmail(emailToken.getEmail());

        if (existingEmail != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration/email";
        }

       // User userForToken = userRepositoryDto.saveNewUser(user);

        EmailToken token = new EmailToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setEmail(emailToken.getEmail());
        token.setExpiryDate(30);
        emailTokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("testwebmarketplace@gmail.com");
        mail.setTo(token.getEmail());
        mail.setSubject("Email confirming request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://memorynotfound.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/confirm-account?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);


        return "redirect:/logout";
    }

}
