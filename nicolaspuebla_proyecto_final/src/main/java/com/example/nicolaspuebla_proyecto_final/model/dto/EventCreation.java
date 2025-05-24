package com.example.nicolaspuebla_proyecto_final.model.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "event_type",
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = MatchCreation.class, name = "match"),
    @JsonSubTypes.Type(value = TrainingCreation.class, name = "training")
})
public class EventCreation {
    private String event_type;
    private Long teamId;
    private Double latitude;
    private Double longitude;
    private String date;

    public EventCreation(){}

    public EventCreation(String event_type, Long teamId, Double latitude, Double longitude, String date) {
        this.event_type = event_type;
        this.teamId = teamId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
