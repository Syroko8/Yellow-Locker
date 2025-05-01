package com.example.nicolaspuebla_proyecto_final.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Match;
import com.example.nicolaspuebla_proyecto_final.service.EventService;
import com.example.nicolaspuebla_proyecto_final.service.TeamService;

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
}
