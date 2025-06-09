package com.example.nicolaspuebla_proyecto_final.model.dto;

/**
 * Clase que contiene la información de inicio de sesión de un usuario.
 */
public class LoginRequest {
    private String email;
    private String passwd;

    public LoginRequest(){}

    public LoginRequest(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }    
}
