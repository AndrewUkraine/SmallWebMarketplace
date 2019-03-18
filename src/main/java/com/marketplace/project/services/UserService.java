package com.marketplace.project.services;

import com.marketplace.project.dao.jpadatarepository.UserRepository;
import com.marketplace.project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public Iterable<User> findAll(){
//        return userRepository.findAll();
//    }
//
//    public List<User> findAll(Sort sort) {
//        return userRepository.findAll(sort);
//    }
//
//    public User save(User user) {
//        return userRepository.save(user);
//    }
//
//    public Optional<User> findById(Integer integer) {
//        return userRepository.findById(integer);
//    }
//
//    public boolean existsById(Integer integer) {
//        return userRepository.existsById(integer);
//    }
//
//    public long count() {
//        return userRepository.count();
//    }
//
//    public void deleteById(Integer integer) {
//        userRepository.deleteById(integer);
//    }
//
//    public void delete(User entity) {
//        userRepository.delete(entity);
//    }
//
//    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return userRepository.findAll(example, pageable);
//    }
//
//    public <S extends User> boolean exists(Example<S> example) {
//        return userRepository.exists(example);
//    }
//
//    public User findUserByEmail(String email) {
//        return userRepository.findUserByEmail(email);
//    }
//
//    public List<User> findByCity(String city) {
//        return userRepository.findByCity(city);
//    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
    }

//    public User getWithOffers(int id) {
//        return userRepository.getWithOffers(id);
//    }

//    public void refresh(User user) {
//        userRepository.refresh(user);
//    }

