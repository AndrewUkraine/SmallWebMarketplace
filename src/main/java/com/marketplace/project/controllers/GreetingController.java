package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.ImageRepository;
import com.marketplace.project.dao.jpadatarepository.OfferRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.User;
import com.marketplace.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping(value = GreetingController.REST_URL)
public class GreetingController {

    static final String REST_URL = "/user";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private OfferRepository offerRepository;

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "";
    }

    // Add new User
    @GetMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String city, @RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam String phone, @RequestParam String last_name) {

        User user = new User();
        user.setCity(city);
        user.setEmail(email);
        user.setFirsName(name);
        user.setPassword(password);
        user.setPhone(phone);
        user.setSecondName(last_name);

        userRepository.save(user);
        return "Saved";
    }

    //Update User
    @GetMapping(path = "/upd/{id}")
    public @ResponseBody
    User updUser(@RequestParam String city, @RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam String phone, @RequestParam String last_name, @PathVariable Integer id) {

        User u = userRepository.getOne(id);
        u.setCity(city);
        u.setEmail(email);
        u.setFirsName(name);
        u.setPassword(password);
        u.setPhone(phone);
        u.setSecondName(last_name);

        return userRepository.save(u);
    }


    //Get User by Id
    @GetMapping(value = "/{id}")
    public String getById(@PathVariable("id") int id, Model model) {

        userRepository.findById(id).ifPresent(o -> model.addAttribute("users", o));

        return "allUsers";
    }


    //delet User By Id
    @GetMapping(path = "/delete/{id}")
    public @ResponseBody
    String deleteById(@PathVariable Integer id) {
        userRepository.deleteById(id);

        return "User is deleted";
    }

    //get User By Email
    @GetMapping(path = "/email/{email}")
    public @ResponseBody
    User findByEmail(@PathVariable String email, Model model) {
        User user = userRepository.findUserByEmail(email);
        model.addAttribute("users", userRepository.findUserByEmail(email));
        return user;
    }


    //delete Offer
    @GetMapping(path = "/offer/{id}")
    public @ResponseBody
    void deleteOfferById(@PathVariable Integer id) {
        offerRepository.deleteById(id);
    }


    //add Offer
    @GetMapping(path = "/addoffer")
    public @ResponseBody
    String addNewOffer(@RequestParam String condition, @RequestParam String description, @RequestParam BigDecimal price, @RequestParam Boolean status, @RequestParam String title, @RequestParam User sellerId) {

        Offer offer = new Offer();
        offer.setCondition(condition);
        offer.getCreationTimeAndDate();
        offer.setOfferDescription(description);
        offer.setPrice(price);
        offer.setStatus(status);
        offer.setTitle(title);

        offer.setSeller(sellerId);

        offerRepository.save(offer);
        return "Saved Offer";
    }

    //update Offer
    @GetMapping(path = "/updoffer/{id}")
    public @ResponseBody
    String updOffer(@RequestParam String condition, @RequestParam String description, @RequestParam BigDecimal price, @RequestParam Boolean status, @RequestParam String title, Integer id) {

        Offer offer = offerRepository.getOne(id);
        offer.setCondition(condition);
        offer.getCreationTimeAndDate();
        offer.setOfferDescription(description);
        offer.setPrice(price);
        offer.setStatus(status);
        offer.setTitle(title);

        offerRepository.save(offer);
        return "Updated Offer";
    }


    //get All Offers
    @GetMapping(path = "/alloffers")
    public @ResponseBody
    Iterable<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    //find all offer by title
    @GetMapping(path = "/title/{title}")
    public @ResponseBody
    Iterable<Offer> findByTitle(@PathVariable String title) {
        Iterable<Offer> offers = offerRepository.findByTitle(title);
        return offers;

    }


    //get All users
    @GetMapping(path = "/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "allUsers";
    }


    @GetMapping(path = "/image")
    public @ResponseBody
    String addNewImage(@RequestParam String name, @RequestParam String path) {

        Image image = new Image();
        image.setName(name);
        image.setPath(path);
        imageRepository.save(image);

        return "SaveImage";
    }

    //get Image
    @GetMapping(path = "/getimage")
    public @ResponseBody
    Iterable<Image> getAllUsers(Image name, Model model) {
        model.addAttribute("name", name);
        return imageRepository.findAll();
    }
}
