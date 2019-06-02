package com.marketplace.project.services;

import com.marketplace.project.dao.jpadatarepository.ImageRepository;
import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    //Save the uploaded file to this folder
    @Value("${upload.pathForAvatar}")
    private String uploadPathForAvatar;

    private Offer imageOffer;

    public Offer getImageOffer() {
        return imageOffer;
    }

    public void setImageOffer(Offer imageOffer) {
        this.imageOffer = imageOffer;
    }

    public Image getFile(Integer id) throws IOException {
        return imageRepository.findById(id)
                .orElseThrow(() -> new IOException("File not found with id " + id));
    }

    public <S extends Image> S save(S entity) {
        return imageRepository.save(entity);
    }


    public void saveAvatar(@RequestParam("file") MultipartFile file, User user)  throws IOException {

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

            save(image);

            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
