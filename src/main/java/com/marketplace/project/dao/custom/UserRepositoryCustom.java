package com.marketplace.project.dao.custom;

import com.marketplace.project.entities.User;

import java.util.List;

public interface UserRepositoryCustom {

    User findUserByEmail(String email);

    List<User> findByCity(String city);


//    void refresh(User user);
}
