package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import org.springframework.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class MobileUser extends User {

    @NonNull
    private String secondSurname;
    private Date birthDate;
    private int age;
    @OneToOne(mappedBy = "user")
    private AsignedPosition asignedPosition;
    @ManyToMany
    @JoinTable(
        name = "user_team",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teamList = new ArrayList<>();
    @OneToMany(mappedBy = "user_id")
    private List<Message> messages = new ArrayList<>();
    @OneToMany(mappedBy = "user_id")
    private List<TeamRol> teamRoles = new ArrayList<>();

    public MobileUser(){}

    public MobileUser(String name, String surname, String email, String password, boolean disabled,
            String secondSurname, Date birthDate) {
        super(name, surname, email, password, disabled);
        this. secondSurname = secondSurname;
        this. birthDate = birthDate;
        this.age = calcularEdad(birthDate);
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
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

    private int calcularEdad(Date fechaNacimiento) {
        LocalDate nacimiento = fechaNacimiento.toLocalDate();
        LocalDate ahora = LocalDate.now();
        return Period.between(nacimiento, ahora).getYears();
    }
}
