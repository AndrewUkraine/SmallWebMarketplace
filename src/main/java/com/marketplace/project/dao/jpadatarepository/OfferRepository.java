package com.marketplace.project.dao.jpadatarepository;

import com.marketplace.project.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Integer> {

    List<Offer> findByTitle (String title);

}
