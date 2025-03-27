package com.example.nicolaspuebla_proyecto_final.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nicolaspuebla_proyecto_final.model.apiModels.LoginRequest;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.User;
import com.example.nicolaspuebla_proyecto_final.service.UserService;

import jakarta.persistence.NoResultException;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping("/login")
    public ResponseEntity<User> logIn(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.getUserByEmail(loginRequest.getEmail());
            Boolean auth = (user.getPassword().equals(loginRequest.getPasswd())) && (!user.isDisabled()) ? true : false;
            ResponseEntity<User> response = auth ?  
                ResponseEntity.ok().body(user) : 
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            return response;
        }catch (NoResultException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        try {
            userService.createUser(user);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/disable/{id}")
    public ResponseEntity<User> disableUser(@PathVariable Long userId) {
        try{
            User user = userService.getUser(userId);
            user.setDisabled(true);
            userService.updateUser(user);
    
            return ResponseEntity.ok().body(null);
        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    } 
    
    @GetMapping("/team_users")
    public ResponseEntity<List<User>> getTeamUsers(@RequestBody List<Long> userIdList) {
        try {
            ArrayList<User> users = new ArrayList<>();

            for(Long user_id : userIdList){
                users.add(userService.getUser(user_id));
            }
            return ResponseEntity.ok().body(users);
        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUse(@PathVariable Long userId) {
        
        try {
            User user = userService.getUser(userId);

            return ResponseEntity.ok().body(user);
        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}
