package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@DiscriminatorColumn(name = "event_type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "event_type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Match.class, name = "match"),
    @JsonSubTypes.Type(value = Training.class, name = "training")
})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JsonIgnoreProperties("eventList")
    @JoinColumn(name = "team_id")
    private Team team;
    private String address;
    private Double latitude;
    private Double longitude;
    private OffsetDateTime date;

    public Event(){}

    public Event(Team team, String address, Double latitude, Double longitude, String date) {
        this.team= team;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = getOffsetDateTime(date);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public OffsetDateTime getOffsetDateTime(String dateTimeStr){
        DateTimeFormatter formater = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime dateTime = OffsetDateTime.parse(dateTimeStr, formater);
        return dateTime.withOffsetSameInstant(ZoneOffset.UTC);
    }
}
