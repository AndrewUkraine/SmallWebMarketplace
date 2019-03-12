package com.marketplace.project.services;

import com.marketplace.project.dao.jpadatarepository.ImageRepository;
import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

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

}
