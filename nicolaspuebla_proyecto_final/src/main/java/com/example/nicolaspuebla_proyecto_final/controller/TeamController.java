package com.example.nicolaspuebla_proyecto_final.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRol;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRolHolder;
import com.example.nicolaspuebla_proyecto_final.model.dto.MemberListElement;
import com.example.nicolaspuebla_proyecto_final.model.dto.TeamInfo;
import com.example.nicolaspuebla_proyecto_final.model.dto.TeamNameListResponse;
import com.example.nicolaspuebla_proyecto_final.service.TeamService;
import com.example.nicolaspuebla_proyecto_final.service.UserService;

import jakarta.persistence.NoResultException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Event;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.MobileUser;
import com.example.nicolaspuebla_proyecto_final.service.EventService;
import com.example.nicolaspuebla_proyecto_final.service.TeamRolService;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Player;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Coach;

@RestController
@RequestMapping("api/team")
public class TeamController {

    @Autowired
    TeamService teamService;
    @Autowired
    EventService eventService;
    @Autowired
    TeamRolService teamRolService;
    @Autowired
    UserService userService;

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

    @GetMapping("/ex/{id}")
    public ResponseEntity<TeamInfo> getTeamExtended(@PathVariable Long id) {
        try {
            Team team = teamService.getTeamWithMembers(id);
            List<Event> events =  eventService.getTeamEvents(team);
            System.err.println("");
            return ResponseEntity.ok().body(new TeamInfo(team, events));
        } catch (Exception e) { 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping()
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

    @PostMapping("/join")
    public ResponseEntity<MobileUser> joinTeam(@RequestParam Long teamId, @RequestParam Long userId) {
        try {
            TeamRolHolder trh = teamService.addUser(teamId, userId);
            teamRolService.createTeamRol(new Player(trh.getUser(), trh.getTeam()));
            return ResponseEntity.ok().body(trh.getUser());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }    

    @GetMapping("/members/{id}")
    public ResponseEntity<List<MemberListElement>> getTeamMembersWithRoles(@PathVariable Long id) {
        try {
            Team team = teamService.getTeam(id);
            ArrayList<MemberListElement> members = new ArrayList<>();

            for(TeamRol rol : team.getTeamRoles()){
                String rolType = "";

                if(rol instanceof Player){
                    rolType = "Player";
                } else if(rol instanceof Coach){
                    rolType = "Coach";
                } else {
                    rolType = "Captain";
                }

                members.add(new MemberListElement(rolType, ( MobileUser)userService.getUser(rol.getId().getUserId())));
            }
            return ResponseEntity.ok().body(members);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }     
    }

    @GetMapping("/names")
    public ResponseEntity<TeamNameListResponse> getTeamNames() {
        try {
            List<String> names = teamService.getTeamNames(); 
            return ResponseEntity.ok().body(new TeamNameListResponse(names));  
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);   
        }
    }
    
}