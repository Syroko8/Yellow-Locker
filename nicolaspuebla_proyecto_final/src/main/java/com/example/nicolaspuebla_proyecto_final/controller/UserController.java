package com.example.nicolaspuebla_proyecto_final.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.nicolaspuebla_proyecto_final.model.User;
import com.example.nicolaspuebla_proyecto_final.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping("/team_users")
    public ResponseEntity<List<User>> getTeamUsers(@RequestBody List<Long> userIdList) {
        try {
            ArrayList<User> users = new ArrayList<>();

            for(Long user_id : userIdList){
                users.add(userService.getUser(user_id));
            }
            return ResponseEntity.ok().body(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
