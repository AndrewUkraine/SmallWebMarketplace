package com.marketplace.project.dao.jpadatarepository;

import com.marketplace.project.entities.Image;
import com.marketplace.project.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> getAllByImageOffer (Optional<Offer> id);
    List<Image> deleteAllByImageOffer (Optional<Offer> id);


    @Query(value = "SELECT * FROM image WHERE image_offer_id like ?", nativeQuery = true)
    List<Image> findAllByImageOffer (Integer id);
}
