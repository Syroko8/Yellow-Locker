package com.example.nicolaspuebla_proyecto_final.model;

import java.sql.Date;
import java.util.Calendar;
import org.springframework.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class MobileUser extends User {

    @NonNull
    private String SecondSurname;
    private Date BirthDate;
    private int age;
    @OneToOne(mappedBy = "user")
    private AsignedPosition asignedPosition;

    public MobileUser(){}

    public MobileUser(String name, String surname, String email, String password, boolean disabled,
            String secondSurname, Date birthDate) {
        super(name, surname, email, password, disabled);
        SecondSurname = secondSurname;
        BirthDate = birthDate;
        this.age = calcularEdad(birthDate);
    }

    public String getSecondSurname() {
        return SecondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        SecondSurname = secondSurname;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int calcularEdad(Date fechaNacimiento) {
        Date fechaActual = new Date(System.currentTimeMillis());

        Calendar calNacimiento = Calendar.getInstance();
        calNacimiento.setTime(fechaNacimiento);

        Calendar calActual = Calendar.getInstance();
        calActual.setTime(fechaActual);

        int edad = calActual.get(Calendar.YEAR) - calNacimiento.get(Calendar.YEAR);

        // Ajustar si aún no ha cumplido años este año.
        if (calActual.get(Calendar.MONTH) < calNacimiento.get(Calendar.MONTH) ||
           (calActual.get(Calendar.MONTH) == calNacimiento.get(Calendar.MONTH) &&
            calActual.get(Calendar.DAY_OF_MONTH) < calNacimiento.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }
        return edad;
    }
}
