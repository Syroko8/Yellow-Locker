package com.example.nicolaspuebla_proyecto_final.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Token;
import com.example.nicolaspuebla_proyecto_final.service.TokenService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @GetMapping("/all")
    public ResponseEntity<List<Token>> getAllTokens() {
        return ResponseEntity.ok().body(tokenService.getAll());
    }
    
    @DeleteMapping("/delete/{token_id}")
    public ResponseEntity<String> deleteToken(@PathVariable Long token_id ){
        try {
            tokenService.deleteToken(token_id);
            return ResponseEntity.ok().body("Deleted token with id:" + token_id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/deleteByT/{token}")
    public ResponseEntity<String> deleteTokenByToken(@PathVariable String token ){
        try {
            tokenService.deleteTokenByToken(token);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
