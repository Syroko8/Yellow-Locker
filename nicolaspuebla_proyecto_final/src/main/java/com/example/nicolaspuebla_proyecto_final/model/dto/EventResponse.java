package com.example.nicolaspuebla_proyecto_final.model.dto;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Event;

/**
 * Clase que representa la respuesta que transfiere un evento a la aplicación.
 */
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
