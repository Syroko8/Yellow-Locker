package com.example.nicolaspuebla_proyecto_final.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.nicolaspuebla_proyecto_final.model.apiModels.LandingScreenTeams;
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

    @GetMapping
    public ResponseEntity<List<Team>> getTeams() {
        try {
            List<Team> teams = teamService.getTeams();
            return ResponseEntity.ok().body(teams);
        } catch (NoResultException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/user_teams/{userId}")
    public ResponseEntity<List<LandingScreenTeams>> getUserTeams(@PathVariable List<Long> teamId) {
        try {
            ArrayList<LandingScreenTeams> teams = new  ArrayList<>();

            for(Long id: teamId){
                Team team = teamService.getTeam(id);
                LandingScreenTeams newLandingTeam = new LandingScreenTeams(
                    team.getId(), 
                    team.getName(), 
                    team.getLocality(), 
                    team.getLogo(),
                    team.getSport()    
                );
                teams.add(newLandingTeam);
            }

            return ResponseEntity.ok().body(teams);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }        
    }
    
}
