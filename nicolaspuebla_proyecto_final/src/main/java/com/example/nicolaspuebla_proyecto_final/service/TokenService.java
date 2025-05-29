package com.example.nicolaspuebla_proyecto_final.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Token;
import com.example.nicolaspuebla_proyecto_final.repository.TokenRepository;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token saveToken(Token newToken){
        try {
            tokenRepository.save(newToken);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return newToken;
    }

    public Token getTokenById(Long tokenId){
        try {
            Token token = tokenRepository.getReferenceById(tokenId);
            return token;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Token getTokenByToken(String token) throws Exception{
        try {
            Token foundToken = tokenRepository.getTokenByToken(token);
            return foundToken;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public Token getTokenByUserId(Long userId){
        try {
            Token token = tokenRepository.getTokenByUserId(userId);
            return token;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void deleteToken(Long tokenId){
        try {
            tokenRepository.deleteById(tokenId);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteTokenByToken(String token){
        try {
            tokenRepository.deleteByToken(token);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Token> getAll(){
        return tokenRepository.findAll();
    }
}
