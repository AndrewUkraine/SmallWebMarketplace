package com.marketplace.project.dao.jpadatarepository;

import com.marketplace.project.entities.User;
import com.marketplace.project.web.dto.UserRegistrationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    @Modifying
    @Query(value = "update User u set u.password = :password where u.id = :id", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("id") Integer id);

    User save(UserRegistrationDto registration);


}
