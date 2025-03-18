package com.example.nicolaspuebla_proyecto_final.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private boolean disabled;
    @OneToMany(mappedBy = "user_id")
    private List<Message> messages = new ArrayList<>();;
    @OneToMany(mappedBy = "destinatary_id")
    private List<Notification> notifications = new ArrayList<>();;
    @OneToMany(mappedBy = "user_id")
    private List<TeamRol> teamRoles = new ArrayList<>();
    @OneToOne(mappedBy = "user_id")
    private Token token = null;


    public User(){}

    public User(String name, String surname, String email, String password, boolean disabled) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.disabled = disabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
