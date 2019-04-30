package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.EmailTokenRepository;
import com.marketplace.project.entities.EmailToken;
import com.marketplace.project.entities.PasswordEmailToken;
import com.marketplace.project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
public class EmailConfirmController {


    @Autowired
    private EmailTokenRepository emailTokenRepository;

    @RequestMapping(value="/confirm-account")
    public String handlePasswordReset(@ModelAttribute ("confirm-account") @Valid EmailToken form) {

        PasswordEmailToken token = emailTokenRepository.findByToken(form.getToken());
        User user = token.getUser();
        user.setActive(true);
        emailTokenRepository.delete(token);

       return "/confirm-email";

    }

}
