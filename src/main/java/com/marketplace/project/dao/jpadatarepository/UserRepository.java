package com.marketplace.project.dao.jpadatarepository;

import com.marketplace.project.dao.custom.UserRepositoryCustom;
import com.marketplace.project.dao.custom.UserRepositoryImpl;
import com.marketplace.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {


}
