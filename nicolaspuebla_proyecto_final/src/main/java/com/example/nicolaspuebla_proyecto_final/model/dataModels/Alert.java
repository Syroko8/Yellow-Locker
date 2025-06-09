package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.sql.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Clase para la entidad Alert, que representa una notificación de alerta.
 */
@Entity
@DiscriminatorValue(value = "alert")
public class Alert extends Notification {

    private String msg;

    public Alert(){}

    public Alert(User destinatary, Date creationDate, String msg) {
        super(destinatary, creationDate);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
