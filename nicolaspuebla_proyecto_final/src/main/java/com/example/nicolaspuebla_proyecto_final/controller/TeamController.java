package com.example.nicolaspuebla_proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.service.TeamService;
import jakarta.persistence.NoResultException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/team")
public class TeamController {

    @Autowired
    TeamService teamService;

   @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable long id) {
        try {
            Team team = teamService.getTeam(id);
            System.err.println(team.getName());
            return ResponseEntity.ok().body(team);
        } catch (NoResultException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}
