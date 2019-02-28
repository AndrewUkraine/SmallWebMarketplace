package com.marketplace.project.controllers;

import com.marketplace.project.dao.jpadatarepository.ImageRepository;
import com.marketplace.project.dao.jpadatarepository.OfferRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.User;
import com.marketplace.project.services.ImageService;
import com.marketplace.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping(value = "images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/Users/andriishatov/IdeaProjects/SmallWebMarketplace/src/main/resources/static/images/";

    @GetMapping(value = "/images")
    public String index() {
        return "upload";
    }

    @PostMapping(value = "/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }



    //add new Image
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
