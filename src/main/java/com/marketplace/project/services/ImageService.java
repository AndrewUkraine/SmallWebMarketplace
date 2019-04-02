package com.marketplace.project.services;

import com.marketplace.project.dao.jpadatarepository.ImageRepository;
import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


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
