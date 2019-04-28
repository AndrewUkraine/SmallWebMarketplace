package com.marketplace.project.dao.jpadatarepository;

import com.marketplace.project.entities.User;
import com.marketplace.project.web.dto.UserRegistrationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface UserRepositoryDto {

    User saveNewUser(UserRegistrationDto registration);


}
