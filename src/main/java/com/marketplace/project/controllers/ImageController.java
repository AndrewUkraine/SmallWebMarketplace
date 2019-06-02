package com.marketplace.project.controllers;

import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.User;
import com.marketplace.project.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    //Save the uploaded file to this folder
    @Value("${upload.pathForAvatar}")
    private String uploadPathForAvatar;

    //private StringBuilder fileNames;

//    public ImageController(StringBuilder fileNames) {
//        this.fileNames = fileNames;
//    }


    public void saveAvatar(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal User user)  throws IOException {

        if (!file.isEmpty() && file.getOriginalFilename()!=null && file.getContentType().equals("image/jpeg")) {
            String uuidFile = UUID.randomUUID().toString();
            Path fileNameAndPath = Paths.get(uploadPathForAvatar, uuidFile + "." + file.getOriginalFilename());
            StringBuilder fileNames = new StringBuilder();
            fileNames.append(uuidFile).append(".").append(file.getOriginalFilename()).append(".").append("jpg");

            Image image = new Image();
            image.setData(file.getBytes());
            image.setName(uuidFile + "." + file.getOriginalFilename());
            image.setPath(uploadPathForAvatar);
            image.setUser(user);

            imageService.save(image);

            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

//    //delete image
//    @RequestMapping(value = "/offer/image/{id}", method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public void deleteImageById(@PathVariable Integer id) {
//
//           Image image =   imageRepository.getOne(id);
//
//            if (!image.getName().isEmpty()) {
//
//                File file = new File(image.getPath() + image.getName());
//
//                if (file.delete()) {
//                    System.out.println(file.getName() + " is deleted!");
//                    imageRepository.delete(image);
//                } else {
//                    System.out.println("Delete operation is failed.");
//                }
//            }
//    }
}
