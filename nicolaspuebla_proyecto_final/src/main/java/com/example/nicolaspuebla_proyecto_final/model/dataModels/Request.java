package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.sql.Date;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Clase que representa una solicitud, un tipo de notificación en el sistema.
 */
@Entity
@DiscriminatorValue(value = "request")
public class Request extends Notification {

    private int sender_id;

    public Request(){}

    public Request(int sender_id, User destinatary, Date creationDate) {
        super(destinatary, creationDate);
        this.sender_id = sender_id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }
}
