package com.marketplace.project.dao.jpadatarepository;

import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> getAllByImageOffer (Optional<Offer> id);
    List<Image> deleteAllByImageOffer (Optional<Offer> id);


}
