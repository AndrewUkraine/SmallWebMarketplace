//package com.marketplace.project.controllers;
//
//import com.marketplace.project.entities.User;
//import com.marketplace.project.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller    // This means that this class is a Controller
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
//public class WorkWithDBController {
//
//    private UserService userService;
//
//    @Autowired
//    public WorkWithDBController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping(path="/add") // Map ONLY GET Requests
//    public @ResponseBody
//    String addNewUser (@RequestParam String name, @RequestParam String email) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        User user = new User();
//        user.setFirsName(name);
//        user.setEmail(email);
//        userService.save(user);
//        return "Saved";
//    }
//
//    @GetMapping(path="/all")
//    public @ResponseBody Iterable<User> getAllUsers() {
//        // This returns a JSON or XML with the users
//        return userService.findAll();
//    }
//
//
//}
