package com.example.nicolaspuebla_proyecto_final.model.dto;

/**
 * Clase que contiene la información necesaria para realizar un registro de usuario.
 */
public class UserSignUp {
    private String name;
    private String surname;
    private String email;
    private String password;
    private Boolean disabled = false;
    private String birthDate;

    public UserSignUp(){}

    public UserSignUp(String name, String surname, String email, String password, Boolean disabled, String birthDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.disabled = disabled;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
