package com.example.nicolaspuebla_proyecto_final.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Match;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Training;
import com.example.nicolaspuebla_proyecto_final.model.dto.EventResponse;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Event;
import com.example.nicolaspuebla_proyecto_final.service.EventService;
import com.example.nicolaspuebla_proyecto_final.service.TeamService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventService eventService;
    @Autowired
    TeamService teamService;

    @GetMapping("/team_matches/{teamId}")
    public ResponseEntity<List<Match>> getTeamMatches(@PathVariable Long teamId) {
        try {
            List<Match> matches = eventService.getTeamMatches(teamService.getTeam(teamId));
            return ResponseEntity.ok().body(matches);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/team_trainings/{teamId}")
    public ResponseEntity<List<Training>> getTeamTrainings(@PathVariable Long teamId) {
        try {
            List<Training> trainings = eventService.getTeamTrainings(teamService.getTeam(teamId));
            return ResponseEntity.ok().body(trainings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Event>> getTeamEvents(@PathVariable Long teamId) {
        try {
            List<Event> events = eventService.getTeamEvents(teamService.getTeam(teamId));
            return ResponseEntity.ok().body(events);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("update")
    public ResponseEntity<EventResponse> updateEvent(@RequestBody Event event, @RequestParam(required = false) Long opponentId) {
        try {
            Event modifiedEvent = eventService.getEventById(event.getId());
            modifiedEvent.setAddress(event.getAddress());
            modifiedEvent.setDate(event.getDate());
            modifiedEvent.setLatitude(event.getLatitude());
            modifiedEvent.setLongitude(event.getLongitude());

            if(modifiedEvent instanceof Match){
                Match match = (Match)event;
                if(opponentId != null){
                    Team oponent = teamService.getTeam(opponentId);
                    ((Match)modifiedEvent).setOpponent(oponent);
                }
                ((Match)modifiedEvent).setOwnGoals(match.getOwnGoals());
                ((Match)modifiedEvent).setOpponentGoals(match.getOpponentGoals());
            }
            eventService.updateEvent(modifiedEvent);
            return ResponseEntity.ok().body(new EventResponse(modifiedEvent));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<EventResponse> deleteEvent(@RequestBody Event event){
        try {
            eventService.deleteEvent(event.getId());
            return ResponseEntity.ok().body(new EventResponse(event));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
