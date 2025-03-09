package com.example.nicolaspuebla_proyecto_final.model;

import java.sql.Date;

import org.springframework.lang.NonNull;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "notification_type")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "destinatary_id")
    private User destinatary_id;
    @NonNull
    private Date creationDate;
 
    public Notification(){}

    public Notification(User destinataryId, Date creationDate) {
        this.destinatary_id = destinataryId;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getDestinataryId() {
        return destinatary_id;
    }

    public void setDestinataryId(User destinatary_id) {
        this.destinatary_id = destinatary_id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
