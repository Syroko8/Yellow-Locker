package com.example.nicolaspuebla_proyecto_final.service;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.User;
import com.example.nicolaspuebla_proyecto_final.repository.UserRepository;

import jakarta.persistence.NoResultException;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id) throws NoResultException, NoSuchElementException{
        return userRepository.findById(id).get();
    }

    public User getUserByEmail(String email) throws NoResultException{
        try {
            System.out.println("Getting user by email: " + email);
            User user = userRepository.findUserByEmail(email);
            return user;
        } catch (NoResultException e) {
            throw new NoResultException();
        } 
    }

    public User createUser(User newUser){
        return userRepository.save(newUser);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
