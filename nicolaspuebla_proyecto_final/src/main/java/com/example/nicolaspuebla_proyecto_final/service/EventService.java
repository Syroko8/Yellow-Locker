package com.example.nicolaspuebla_proyecto_final.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Match;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Event;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event getEventById(Long id){
        return eventRepository.getReferenceById(id);
    }

    public List<Event> getTeamEvents(Team team) throws Exception{
        try {
            return eventRepository.findTeamEvents(team);    
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public List<Match> getTeamMatches(Team team) throws Exception{
        try {
            List<Event> events = eventRepository.findTeamEvents(team);  
            List<Match> matches = new ArrayList<>();
            for(Event event : events){
                if(event instanceof Match){
                    matches.add((Match) event);
                }
            }
            return matches;   
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
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
