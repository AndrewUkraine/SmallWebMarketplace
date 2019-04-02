package com.marketplace.project.dao.jpadatarepository;

import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    @Query(value = "SELECT * FROM offer as o WHERE o.status = true  ORDER BY o.creation_time DESC", nativeQuery = true)
    List<Offer> findAllActiveOffer();

    List<Offer> findByTitle (String title);

    List<Offer> findBySeller (Optional<User> user);

}
