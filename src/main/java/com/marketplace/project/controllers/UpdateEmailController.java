package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.EmailTokenRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.EmailToken;
import com.marketplace.project.entities.Mail;
import com.marketplace.project.entities.User;
import com.marketplace.project.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/updateEmail" )
public class UpdateEmailController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailTokenRepository emailTokenRepository;

    @Autowired
    private EmailService emailService;

    @ModelAttribute("useremail")
    public User getUser(@AuthenticationPrincipal User user) {
        return user;
    }

    @ModelAttribute("emailtoken")
    public EmailToken emailToken() {
        return new EmailToken();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "updateEmail";
    }


    @PostMapping
    public String updateEmail(@AuthenticationPrincipal User user, @ModelAttribute("emailtoken") @Valid EmailToken emailToken, BindingResult result, HttpServletRequest request, Model modell) {

        if (emailToken.getEmail().equals(user.getEmail()))
        {
            result.rejectValue("email", null, "Please, input new email. New e-mail can't match with previous");
            return "updateEmail";
        }


        User existingEmail = userRepository.findByEmail(emailToken.getEmail());

        if (existingEmail != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
            return "registration/email";
        }

        if (result.hasErrors()){
            return "registration/email";
        }

        //if user have token already we delete this token
        else if (user.getEmailToken()!=null){
            EmailToken token = emailTokenRepository.findByToken(user.getEmailToken().getToken());
            emailTokenRepository.delete(token);
        }

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
