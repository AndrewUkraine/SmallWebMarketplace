package com.marketplace.project.dao.jpadatarepository;

import com.marketplace.project.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    @Query(value = "SELECT * FROM password_reset_token WHERE user_id = ?", nativeQuery = true)
    PasswordResetToken findAllByUser(Integer userId);

    //PasswordResetToken findAllByUserAndExpiryDate(Integer userId);

}
