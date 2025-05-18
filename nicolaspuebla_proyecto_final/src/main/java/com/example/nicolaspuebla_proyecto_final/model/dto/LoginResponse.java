package com.example.nicolaspuebla_proyecto_final.model.dto;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.MobileUser;

public class LoginResponse {
    private MobileUser user;
    private String token;

    public LoginResponse(){}

    public LoginResponse(MobileUser user, String token) {
        this.user = user;
        this.token = token;
    }

    public MobileUser getUser() {
        return user;
    }

    public void setUser(MobileUser user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
