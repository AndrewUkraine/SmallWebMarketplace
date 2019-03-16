package com.marketplace.project.dao.jpadatarepository;

import com.marketplace.project.dao.custom.UserRepositoryCustom;
import com.marketplace.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {


}
