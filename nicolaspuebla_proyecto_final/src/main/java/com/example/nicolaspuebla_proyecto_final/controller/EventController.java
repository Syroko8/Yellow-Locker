package com.example.nicolaspuebla_proyecto_final.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Event;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Match;
import com.example.nicolaspuebla_proyecto_final.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

   @GetMapping("/team_matches")
    public ResponseEntity<List<Event>> getTeamMatches(@RequestBody List<Long> eventIdList) {
        try {
            ArrayList<Event> matches = new ArrayList<>();

            for(Long event_id : eventIdList){
                Event event = eventService.getEventById(event_id);
                if(event instanceof Match){
                    matches.add(event);
                }
            }

            return ResponseEntity.ok().body(matches);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
