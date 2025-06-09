package com.example.nicolaspuebla_proyecto_final.model.dto;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.MobileUser;

/**
 * Clase que representa la respuesta para la verificación de un token de usuario móvil.
 */
public class TokenCheckResponse {
    private MobileUser user;

    public TokenCheckResponse(){}

    public TokenCheckResponse(MobileUser user) {
        this.user = user;
    }

    public MobileUser getUser() {
        return user;
    }

    public void setUser(MobileUser user) {
        this.user = user;
    }    
}
