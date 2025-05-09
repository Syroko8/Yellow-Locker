package com.example.nicolaspuebla_proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.example.nicolaspuebla_proyecto_final.model.apiModels.TeamRolLevelResponse;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRolPK;
import com.example.nicolaspuebla_proyecto_final.service.TeamRolService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/team_rol")
public class TeamRolController {

    @Autowired
    TeamRolService teamRolService;

    @PostMapping("/level")
    public ResponseEntity<TeamRolLevelResponse> getTeamRolLevel(@RequestBody TeamRolPK teamRolPK) {
        try {
            return ResponseEntity.ok().body(new TeamRolLevelResponse(teamRolService.getRolLevel(teamRolPK)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
