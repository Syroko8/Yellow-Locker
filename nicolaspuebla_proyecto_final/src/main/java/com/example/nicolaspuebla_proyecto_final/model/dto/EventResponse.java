package com.example.nicolaspuebla_proyecto_final.model.dto;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Event;

public class EventResponse {
    private Event event;

    public EventResponse(){}

    public EventResponse(Event event){
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }    
}
