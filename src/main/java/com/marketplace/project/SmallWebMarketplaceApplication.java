package com.marketplace.project;

import com.marketplace.project.entities.Category;
import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.User;
import com.marketplace.project.entities.UserRole;
import com.marketplace.project.entities.enums.CategoryTypes;
import com.marketplace.project.entities.enums.ConditionType;
import com.marketplace.project.entities.enums.RoleType;
import com.marketplace.project.services.CategoryService;
import com.marketplace.project.services.ImageService;
import com.marketplace.project.services.OfferService;
import com.marketplace.project.services.UserRoleService;
import com.marketplace.project.services.UserService;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//@ComponentScan(value = {"com.marketplace.project.controllers", "com.marketplace.project.services", "com.marketplace.project.dao", "com.marketplace.project.entities"})
@SpringBootApplication
public class SmallWebMarketplaceApplication  {
//implements CommandLineRunner
    @Autowired
    UserService userService;
    @Autowired
    OfferService offerService;
    @Autowired
    ImageService imageService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserRoleService userRoleService;

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    public static void main(String[] args) {
        ApplicationContext appCtx = SpringApplication.run(SmallWebMarketplaceApplication.class, args);
    }


//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//
//        Category category = new Category();
//        category.setCategory(CategoryTypes.ELECTRONIKS.getCategoryType());
//
//        Category category2 = new Category();
//        category2.setCategory(CategoryTypes.MOTORS.getCategoryType());
//
//        Category category3 = new Category();
//        category3.setCategory(CategoryTypes.FASHION.getCategoryType());
//
//        Category category4 = new Category();
//        category4.setCategory(CategoryTypes.HOME_AND_GARDEN.getCategoryType());
//
//        Category category5 = new Category();
//        category5.setCategory(CategoryTypes.SPORTING_GOODS.getCategoryType());
//
//        categoryService.save(category);
//        categoryService.save(category5);
//
//
//        //*******************************************//
//
//        UserRole buyerUserRole = new UserRole();
//        buyerUserRole.setRoleType(RoleType.BUYER.getRoleType());
//
//        UserRole sellerUserRole = new UserRole();
//        sellerUserRole.setRoleType(RoleType.SELLER.getRoleType());
//
//        UserRole adminUserRole = new UserRole();
//        adminUserRole.setRoleType(RoleType.ADMIN.getRoleType());
//
//        userRoleService.save(buyerUserRole);
//        userRoleService.save(sellerUserRole);
//        userRoleService.save(adminUserRole);
//
//        //*******************************************//
//
//        Set<UserRole> userRoles = new HashSet<>();
//        userRoles.add(adminUserRole);
//
//        User user1 = new User();
//        user1.setFirsName("Ivan");
//        user1.setSecondName("Silivanov");
//        user1.setCity("Kiev");
//        user1.setEmail("trulala@dotnet.com");
//        user1.setPhone("096-22-66-999");
//        user1.setPassword("qwerty");
//        user1.setPurchasedItems(new ArrayList<>());
//        user1.setSellList(new ArrayList<>());
//        user1.setUserRoles(userRoles);
//        userService.save(user1);
//
//        //*******************************************//
//
//        Set<UserRole> userRoles2 = new HashSet<>();
//        userRoles2.add(buyerUserRole);
//        userRoles2.add(sellerUserRole);
//
//        //*******************************************//
//
//        User user2 = new User();
//        user2.setFirsName("Ирина");
//        user2.setSecondName("Шрайк");
//        user2.setCity("Киев");
//        user2.setEmail("hsrike@gmail.com");
//        user2.setPhone("096-12-68-979");
//        user2.setPassword("irina");
//        user2.setPurchasedItems(new ArrayList<>());
//        user2.setSellList(new ArrayList<>());
//        user2.setUserRoles(userRoles2);
//        userService.save(user2);
//
//        //*******************************************//
//
//        Offer offer1 = new Offer();
//        offer1.setCategory(category);
//        offer1.setCondition(ConditionType.NEW);
//        offer1.setCreationTimeAndDate(LocalDateTime.now());
//        offer1.setBuyer(user2);
//        offer1.setSeller(user1);
//        offer1.setStatus(true);
//        offer1.setTitle("Laptop Asus");
//        offer1.setPrice(BigDecimal.valueOf(100.00));
//        //offer1.setImages(Arrays.asList());
//        offer1.setOfferDescription("<Hello every body - 1>");
//        offerService.save(offer1);
//
//        Image image = new Image();
//        image.setName("LaptopAsus");
//        image.setPath("static/images/asus.png");
//        image.setImageOffer(offer1);
//        imageService.save(image);
//
//        //*******************************************//
//
//        Offer offer2 = new Offer();
//        offer2.setCategory(category5);
//        offer2.setCondition(ConditionType.NEW);
//        offer2.setCreationTimeAndDate(LocalDateTime.now());
//        offer2.setBuyer(user1);
//        offer2.setSeller(user2);
//        offer2.setStatus(true);
//        offer2.setTitle("Sell auto Zapor");
//        offer2.setPrice(BigDecimal.valueOf(50.00));
//        //offer2.setImages(Arrays.asList());
//        offer2.setOfferDescription("<Hello every body - 2>");
//        offerService.save(offer2);
//
//        Image image2 = new Image();
//        image2.setName("Zapor-1");
//        image2.setPath("static/images/zaporAuto-1.png");
//        image2.setImageOffer(offer2);
//        imageService.save(image2);
//
//        Image image3 = new Image();
//        image3.setName("Zapor-2");
//        image3.setPath("static/images/zaporAuto-2.png");
//        image3.setImageOffer(offer2);
//        imageService.save(image3);
//
//        //*******************************************//
//
//
//    }
}

