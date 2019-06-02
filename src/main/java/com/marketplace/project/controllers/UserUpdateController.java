package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.*;
import com.marketplace.project.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/update-user")
public class UserUpdateController {

    @Autowired
    private UserRepository userRepository;

    //return UserRegistrationDto, but can do like this (value = "updateUser") below
    /*@ModelAttribute("updateUser")
    public UserRegistrationDto updateUser() {
       return  new UserRegistrationDto();
    }*/

    //mapping to html template useful when send empty form
    @GetMapping
    public String showUpdatePage() {
        return "update-user";
    }

    @ModelAttribute("updateUser")
    public UserRegistrationDto updateUser(@AuthenticationPrincipal User user,  Model model) {


        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setFirsName(user.getFirsName());
        userRegistrationDto.setSecondName(user.getSecondName());
        userRegistrationDto.setCity(user.getCity());
        userRegistrationDto.setPassword(user.getPassword());
        userRegistrationDto.setEmail(user.getEmail());
        userRegistrationDto.setPhone(user.getPhone());
        userRegistrationDto.setUserAvatar(user.getUserAvatar());

        //model.addAttribute("user", userRegistrationDto);
        return userRegistrationDto;
    }

    // Update User
    @PostMapping
    @Transactional
    public String updateUser(@AuthenticationPrincipal User user, @ModelAttribute ("updateUser") @Valid UserRegistrationDto userupdate, BindingResult results) {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        userRepository.findById(user.getId());

        User existingPhone = userRepository.findByPhone(userupdate.getPhone());

        if (existingPhone!=null && !existingPhone.getId().equals(user.getId()))
        {
            results.rejectValue("phone", null, "You can't use this phone. There is already an account registered with that phone");
            return "update-user";
        }

        if (userupdate.getUpdateNewPassword()!=null && !userupdate.getUpdateNewPassword().isEmpty())
        {

            if (!userupdate.getUpdateNewPassword().equals(userupdate.getMatchingPassword()))
            {
                results.rejectValue("updateNewPassword", null, "The password fields must match");
                results.rejectValue("matchingPassword", null, "The password fields must match");
                return "update-user";
            }

            if (passwordEncoder.matches(userupdate.getPassword(), user.getPassword()))
            {
               user.setPassword(passwordEncoder.encode(userupdate.getUpdateNewPassword()));
            }

            else {
                results.rejectValue("password", null, "You must confirm current password");
                return "update-user";
            }
        }

        if (userupdate.getPhone().isEmpty()) {
            results.hasErrors();
            return "update-user";
        }

        if (userupdate.getFirsName().isEmpty()) {
            results.hasErrors();
            return "update-user";
        }


        if (userupdate.getSecondName().isEmpty()){
            return "update-user";
        }


        if(userupdate.getCity().isEmpty()){
            results.hasErrors();
            return "update-user";
        }

//        if (results.hasErrors()){
//            return "update-user";
//        }

        user.setPhone(userupdate.getPhone());
        user.setFirsName(userupdate.getFirsName());
        user.setSecondName(userupdate.getSecondName());
        user.setCity(userupdate.getCity());
        userRepository.save(user);

        return "redirect:/update-user?success";
    }




    @GetMapping( "update-user")
    public String updateUserPassword(@AuthenticationPrincipal User user, @ModelAttribute UserRegistrationDto userRegistrationDto,  Model model) {

       userRegistrationDto.setPassword(user.getPassword());

        model.addAttribute("updateuserpassword", userRegistrationDto);
        return "update-user";
    }

}
