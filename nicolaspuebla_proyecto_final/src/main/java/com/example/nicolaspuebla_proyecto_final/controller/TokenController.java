package com.example.nicolaspuebla_proyecto_final.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.MobileUser;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Token;
import com.example.nicolaspuebla_proyecto_final.model.dto.TokenCheckResponse;
import com.example.nicolaspuebla_proyecto_final.service.TokenService;
import com.example.nicolaspuebla_proyecto_final.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/token")
public class TokenController {

    @Autowired
    TokenService tokenService;
    @Autowired
    UserService userService;

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

    @GetMapping("/token_check")
    public ResponseEntity<TokenCheckResponse> tokenCheck(@RequestParam Long userId, @RequestParam String token) {
        try {
            Token tokenObj = tokenService.getTokenByToken(token);
            if((tokenObj != null) && (tokenObj.getUser_id() == userId)){
                MobileUser user = (MobileUser) userService.getUser(userId);
                return ResponseEntity.ok().body(new TokenCheckResponse(user));
            } else {
                return ResponseEntity.ok().body(new TokenCheckResponse());
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }    

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String token) {
        try {
            tokenService.deleteTokenByToken(token); 
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
