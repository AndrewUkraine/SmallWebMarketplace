package com.marketplace.project.dao.jpadatarepository;

import com.marketplace.project.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
