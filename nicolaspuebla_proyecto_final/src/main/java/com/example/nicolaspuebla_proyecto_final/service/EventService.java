package com.example.nicolaspuebla_proyecto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nicolaspuebla_proyecto_final.model.Event;
import com.example.nicolaspuebla_proyecto_final.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public Event getEventById(Long id){
        return eventRepository.getReferenceById(id);
    }

    public List<Event> getTeamEvents(Long team_id){
        return eventRepository.findTeamEvents(team_id);
    }

    public Event createEvent(Event newEvent){
        return eventRepository.save(newEvent);
    }

    public Event updateEvent(Event event){
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }
}
