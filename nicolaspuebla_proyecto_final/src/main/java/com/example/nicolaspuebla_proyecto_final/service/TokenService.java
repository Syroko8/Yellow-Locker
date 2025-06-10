package com.example.nicolaspuebla_proyecto_final.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Token;
import com.example.nicolaspuebla_proyecto_final.repository.TokenRepository;

/**
 * Servicio que maneja las operaciones relacionadas con los tokens de usuario.
 */
@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    /**
     * Guarda un nuevo token o actualiza uno existente.
     * @param newToken El token a guardar.
     * @return El token guardado.
     */
    public Token saveToken(Token newToken){
        try {
            tokenRepository.save(newToken);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return newToken;
    }

    /**
     * Obtiene un token por su ID.
     * @param tokenId Identificador del token.
     * @return El token correspondiente al ID.
     */
    public Token getTokenById(Long tokenId){
        try {
            Token token = tokenRepository.getReferenceById(tokenId);
            return token;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene un token por su valor.
     * @param token El valor del token.
     * @return El token correspondiente al valor.
     */
    public Token getTokenByToken(String token) throws Exception{
        try {
            Token foundToken = tokenRepository.getTokenByToken(token);
            return foundToken;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Obtiene un token por el ID del usuario asociado.
     * @param userId Identificador del usuario.
     * @return El token correspondiente al ID del usuario.
     */
    public Token getTokenByUserId(Long userId){
        try {
            Token token = tokenRepository.getTokenByUserId(userId);
            return token;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Elimina un token por su ID.
     * @param tokenId Identificador del token a eliminar.
     */
    public void deleteToken(Long tokenId){
        try {
            tokenRepository.deleteById(tokenId);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Elimina un token por su valor.
     * @param userId El identificador del usuario cuyo token se elimina.
     */
    public void deleteTokenByUserId(Long userId){
        try {
            tokenRepository.deleteByUserId(userId);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Obtiene todos los tokens almacenados.
     * @return Lista de todos los tokens.
     */
    public List<Token> getAll(){
        return tokenRepository.findAll();
    }
}
