package com.example.nicolaspuebla_proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Captain;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Coach;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.MobileUser;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Player;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRol;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRolPK;
import com.example.nicolaspuebla_proyecto_final.model.dto.TeamRolLevelResponse;
import com.example.nicolaspuebla_proyecto_final.service.TeamRolService;
import com.example.nicolaspuebla_proyecto_final.service.TeamService;
import com.example.nicolaspuebla_proyecto_final.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


enum TeamRoles{
    Player,
    Coach,
    Captain
}

@Controller
@RequestMapping("api/team_rol")
public class TeamRolController {

    @Autowired
    TeamRolService teamRolService;
    @Autowired 
    UserService userService;
    @Autowired
    TeamService teamService;


    @PostMapping("/level")
    public ResponseEntity<TeamRolLevelResponse> getTeamRolLevel(@RequestBody TeamRolPK teamRolPK) {
        try {
            String teamRol = teamRolService.getRolLevel(teamRolPK);
            return ResponseEntity.ok().body(new TeamRolLevelResponse(teamRol));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/change")
    public ResponseEntity<TeamRol> changeTeamRol(@RequestParam Long userId, @RequestParam Long teamId, @RequestParam TeamRoles rolType) {
        try {
            TeamRol oldTeamRol = teamRolService.getTeamRol(new TeamRolPK(userId, teamId));
            teamRolService.removeRol(oldTeamRol);
            
            MobileUser user = (MobileUser) userService.getUser(userId);
            Team team = teamService.getTeam(teamId);    
            TeamRol newTeamRol = new TeamRol();

            switch (rolType) {
                case Player:
                    newTeamRol = new Player(user, team);
                    break;
                case Coach:
                    newTeamRol = new Coach(user, team);
                    break;
                case Captain:
                    newTeamRol = new Captain(user, team);
                    break;
                default:
                    break;
            }

            teamRolService.createTeamRol(newTeamRol);
            return ResponseEntity.ok().body(newTeamRol);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);            
        }
    }
}
