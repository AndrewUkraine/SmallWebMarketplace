package com.marketplace.project.dao.jpadatarepository;

import com.marketplace.project.entities.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTokenRepository extends JpaRepository<EmailToken, Long> {

    EmailToken findByToken(String token);

    @Query(value = "SELECT * FROM email_token WHERE user_id = ?", nativeQuery = true)
    EmailToken findAllByUser(Integer userId);

}
