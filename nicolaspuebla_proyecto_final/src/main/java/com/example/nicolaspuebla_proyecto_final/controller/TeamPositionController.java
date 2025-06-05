package com.example.nicolaspuebla_proyecto_final.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamPosition;
import com.example.nicolaspuebla_proyecto_final.service.TeamPositionService;
import com.example.nicolaspuebla_proyecto_final.service.TeamService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequestMapping("/api/team_position")
public class TeamPositionController {

    @Autowired
    TeamPositionService teamPositionService;
    @Autowired
    TeamService teamService;

    @GetMapping("/{teamId}")
    public ResponseEntity<List<TeamPosition>> getTeamPositions(@PathVariable Long teamId) {
        try {
            Team team = teamService.getTeam(teamId);
            List<TeamPosition> list = teamPositionService.getTeamPositions(team); 
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @PostMapping("/create")
    public ResponseEntity<TeamPosition> createTeamPosition(@RequestParam Long teamId, @RequestParam String name) {
        try {
            Team team = teamService.getTeam(teamId);
            TeamPosition newTeamPosition = new TeamPosition(team, name);
            teamPositionService.createTeamPosition(newTeamPosition);
            return ResponseEntity.ok().body(newTeamPosition);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Long> deleteTeamPosition(@RequestParam Long teamPositionId) {
        try {
            teamPositionService.deleteTeamPosition(teamPositionId);
            return ResponseEntity.ok().body(teamPositionId);
        } catch (SQLIntegrityConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
