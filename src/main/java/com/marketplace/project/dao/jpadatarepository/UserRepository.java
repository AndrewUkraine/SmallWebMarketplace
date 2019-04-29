package com.marketplace.project.dao.jpadatarepository;

import com.marketplace.project.entities.Offer;
import com.marketplace.project.entities.User;
import com.marketplace.project.web.dto.UserRegistrationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByPhone(String phone);

    @Modifying
    @Query(value = "update User u set u.password = :password where u.id = :id", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("id") Integer id);

}
