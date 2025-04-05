package com.example.nicolaspuebla_proyecto_final.model.apiModels;

import java.util.ArrayList;
import java.util.List;

public class UserSignUp {
    private String name;
    private String surname;
    private String email;
    private String password;
    private Boolean disabled = false;
    private List<Long> messages = new ArrayList<>();
    private List<Long> notifications = new ArrayList<>();
    private List<Long> teamRoles = new ArrayList<>();

    public UserSignUp(){}

    public UserSignUp(String name, String surname, String email, String password, Boolean disabled, List<Long> messages,
            List<Long> notifications, List<Long> teamRoles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.disabled = disabled;
        this.messages = messages;
        this.notifications = notifications;
        this.teamRoles = teamRoles;
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

    public List<Long> getMessages() {
        return messages;
    }

    public void setMessages(List<Long> messages) {
        this.messages = messages;
    }

    public List<Long> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Long> notifications) {
        this.notifications = notifications;
    }

    public List<Long> getTeamRoles() {
        return teamRoles;
    }

    public void setTeamRoles(List<Long> teamRoles) {
        this.teamRoles = teamRoles;
    }
}
