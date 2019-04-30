package com.marketplace.project.services;

import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.dao.jpadatarepository.UserRepositoryDto;
import com.marketplace.project.entities.User;
import com.marketplace.project.entities.enums.RoleType;
import com.marketplace.project.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class UserServiceImpl implements UserRepositoryDto {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveNewUser(UserRegistrationDto registration) {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        User user = new User();

        user.setFirsName(registration.getFirsName());
        user.setSecondName(registration.getSecondName());
        user.setCity(registration.getCity());
        user.setEmail(registration.getEmail());
        user.setPhone(registration.getPhone());
        user.setActive(false);
        user.setTerms(registration.getTerms());
        user.setCreated(registration.getCreated());
        user.setRoles(Collections.singleton(RoleType.USER));

        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        return userRepository.save(user);
    }
}
