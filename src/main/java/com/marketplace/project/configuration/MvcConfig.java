package com.marketplace.project.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
       // registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/alloffers").setViewName("offers");
        //registry.addViewController("/offers").setViewName("offers");
       // registry.addViewController("/user").setViewName("registrationPage");
    }

}