package com.example.nicolaspuebla_proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPosition;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPositionPK;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.MobileUser;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamPosition;
import com.example.nicolaspuebla_proyecto_final.service.AssignedPositionService;
import com.example.nicolaspuebla_proyecto_final.service.TeamPositionService;
import com.example.nicolaspuebla_proyecto_final.service.TeamService;
import com.example.nicolaspuebla_proyecto_final.service.UserService;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/assigned_position")
public class AssignedPositionController {

    @Autowired
    private AssignedPositionService assignedPositionService;
    @Autowired
    private TeamPositionService teamPositionService;
    @Autowired
    private UserService userService;
    @Autowired 
    private TeamService teamService;

    @GetMapping()
    public ResponseEntity<AssignedPosition> getAssignedPosition(@RequestParam Long userId, @RequestParam Long teamId) {
        try {
            AssignedPosition position = assignedPositionService.getAssignedPosition(new AssignedPositionPK(userId, teamId));
            return ResponseEntity.ok().body(position);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<AssignedPosition> createAssignedPosition(@RequestBody AssignedPosition newAssignedPosition) {
        try {
            AssignedPosition position = assignedPositionService.createAssignedPosition(newAssignedPosition);
            return ResponseEntity.ok().body(position);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/change")
    public ResponseEntity<AssignedPosition> changeAssignedPosition(@RequestParam Long userId, @RequestParam Long teamId ,  @RequestParam Long teamPositionId) {
        try {
            assignedPositionService.deleteAssignedPosition(userId, teamId);
            TeamPosition teamPosition = teamPositionService.getTeamPosition(teamPositionId);
            MobileUser user = (MobileUser) userService.getUser(userId);
            Team team = teamService.getTeam(teamId);

            AssignedPosition newAssignedPosition = new AssignedPosition(user, team, teamPosition);
            AssignedPositionPK pk = new AssignedPositionPK(user.getId(), team.getId());
            newAssignedPosition.setId(pk);
            assignedPositionService.createAssignedPosition(newAssignedPosition);

            return ResponseEntity.ok().body(newAssignedPosition);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }        
    }
}