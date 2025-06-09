package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

/**
 * Clase que representa un usuario móvil en el sistema.
 */
@Entity
public class MobileUser extends User {

    private Date birthDate;
    private int age;
    @ManyToMany
    @JoinTable(
        name = "user_team",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    @JsonIgnoreProperties("members")
    private List<Team> teamList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-messages")
    private List<Message> messages = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<TeamRol> teamRoles = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<AssignedPosition> assignedPositions = new ArrayList<>();
    
    public MobileUser(){}

    public MobileUser(String name, String surname, String email, String password, boolean disabled, Date birthDate) {
        super(name, surname, email, password, disabled);
        this. birthDate = birthDate;
        this.age = calcularEdad(birthDate);
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<AssignedPosition> getAssignedPositions() {
        return assignedPositions;
    }

    public void setAsignedPositions(List<AssignedPosition> assignedPositions) {
        this.assignedPositions = assignedPositions;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<TeamRol> getTeamRoles() {
        return teamRoles;
    }

    public void setTeamRoles(List<TeamRol> teamRoles) {
        this.teamRoles = teamRoles;
    }

    public void removeRol(TeamRol oldRol){
        this.teamRoles.remove(oldRol);
    }

    /**
     * Método que calcula la edad del usuario a partir de su fecha de nacimiento
     * @param fechaNacimiento Fecha de nacimiento del usuario.
     * @return Edad del usuario en años.
     */
    private int calcularEdad(Date fechaNacimiento) {
        LocalDate nacimiento = fechaNacimiento.toLocalDate();
        LocalDate ahora = LocalDate.now();
        return Period.between(nacimiento, ahora).getYears();
    }

    /**
     * Método que añade un rol a la lista de roles del usuario.
     * @param newRol Nuevo rol a añadir al usuario.
     * @return El usuario actual.
     */
    public MobileUser addRol(TeamRol newRol){
        this.teamRoles.add(newRol);
        return this;
    }
}
