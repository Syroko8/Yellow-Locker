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

/**
 * Controlador que maneja las peticiones relacionadas con los tokens de usuario.
 */
@RestController
@RequestMapping("api/token")
public class TokenController {

    @Autowired
    TokenService tokenService;
    @Autowired
    UserService userService;

    /**
     * Método que maneja las peticiones para obtener todos los tokens.
     * @return Una lista con todos los tokens registrados.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Token>> getAllTokens() {
        return ResponseEntity.ok().body(tokenService.getAll());
    }
    
    /**
     * Método que maneja las peticiones para eliminar un token.
     * @param token_id Identificador del token a eliminar.
     * @return Una cadena indicando el resultado de la operación.
     */
    @DeleteMapping("/delete/{token_id}")
    public ResponseEntity<String> deleteToken(@PathVariable Long token_id ){
        try {
            tokenService.deleteToken(token_id);
            return ResponseEntity.ok().body("Deleted token with id:" + token_id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Método que maneja las peticiones para verificar un token.
     * @param userId Identificador del usuario asociado al token.
     * @param token El valor del token a verificar.
     * @return El objeto del usuario asociado al token, si es válido.
     */
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

    /**
     * Método que maneja las peticiones para realizar un logout.
     * @param token El valor del token a eliminar.
     * @return El valor del token eliminado.
     */
    @PostMapping("/logout")
    public ResponseEntity<Long> logout(@RequestParam Long userId) {
        try {
            tokenService.deleteTokenByUserId(userId); 
            return ResponseEntity.ok().body(userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
