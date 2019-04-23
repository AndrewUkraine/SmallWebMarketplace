package com.marketplace.project;

import com.marketplace.project.services.CategoryService;
import com.marketplace.project.services.ImageService;
import com.marketplace.project.services.OfferService;
import com.marketplace.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;


//@ComponentScan(value = {"com.marketplace.project.controllers", "com.marketplace.project.services", "com.marketplace.project.dao", "com.marketplace.project.entities"})
@SpringBootApplication
public class SmallWebMarketplaceApplication  {
//implements CommandLineRunner

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    public static void main(String[] args) {
        ApplicationContext appCtx = SpringApplication.run(SmallWebMarketplaceApplication.class, args);
    }

//for testing
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//
}

