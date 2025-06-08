package com.example.nicolaspuebla_proyecto_final.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Match;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Event;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Training;
import com.example.nicolaspuebla_proyecto_final.repository.EventRepository;

import jakarta.persistence.NoResultException;

/**
 * Servicio que maneja las operaciones relacionadas con los eventos.
 */
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    /**
     * Obtiene un evento por su ID.
     * @param id Identificador del evento.
     * @return El evento correspondiente al ID.
     */
    public Event getEventById(Long id) throws NoResultException, Exception{
        try {
            return eventRepository.findById(id)
            .orElseThrow(() -> new NoResultException());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Obtiene todos los eventos de un equipo.
     * @param team Equipo del cual se desean obtener los eventos.
     * @return Lista de todos los eventos del equipo.
     */
    public List<Event> getTeamEvents(Team team) throws Exception{
        try {
            return eventRepository.findTeamEvents(team);    
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Obtiene todos los partidos de un equipo.
     * @param team Equipo del cual se desean obtener los partidos.
     * @return Lista de todos los partidos del equipo.
     */
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

    /**
     * Obtiene todos los entrenamientos de un equipo.
     * @param team Equipo del cual se desean obtener los entrenamientos.
     * @return Lista de todos los entrenamientos del equipo.
     */
    public List<Training> getTeamTrainings(Team team) throws Exception{
        try {
            List<Event> events = eventRepository.findTeamEvents(team);  
            List<Training> trainings = new ArrayList<>();
            for(Event event : events){
                if(event instanceof Training){
                    trainings.add((Training) event);
                }
            }
            return trainings;   
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Crea un nuevo evento.
     * @param newEvent El nuevo evento a crear.
     * @return El evento creado.
     */
    public Event createEvent(Event newEvent){
        return eventRepository.save(newEvent);
    }

    /**
     * Actualiza un evento existente.
     * @param event El evento con los nuevos datos.
     * @return El evento actualizado.
     */
    public Event updateEvent(Event event) throws Exception{
        try {
            return eventRepository.save(event);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Elimina un evento por su ID.
     * @param id Identificador del evento a eliminar.
     */
    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }
}
