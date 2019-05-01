package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.EmailTokenRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.EmailToken;
import com.marketplace.project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
public class EmailConfirmController {


    @Autowired
    private EmailTokenRepository emailTokenRepository;

    @RequestMapping(value="/confirm-account")
    public String handlePasswordReset(@ModelAttribute ("confirm-account") @Valid EmailToken form, Model model,  BindingResult result) {

        EmailToken token = emailTokenRepository.findByToken(form.getToken());


        if (token == null){
            model.addAttribute("error", "Could not find confirming token.");
        }
        if (token != null && token.isExpired()){
            model.addAttribute("error", "Token has expired, please request a new confirming reset.");
        }
        if (result.hasErrors()){
            return "registration";
        }


        User user = token.getUser();

        if (!user.isActive())
        {
            user.setActive(true);
        }
//       else {
//            user.setEmail(token.getUser().getEmail());
//        }

        emailTokenRepository.delete(token);

       return "/confirm-email";

    }

}
