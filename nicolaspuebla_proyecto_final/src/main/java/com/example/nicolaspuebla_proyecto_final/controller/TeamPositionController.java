package com.example.nicolaspuebla_proyecto_final.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


/**
 * Controlador que maneja las peticiones relacionadas con la entidad TeamPosition.
 */
@Controller
@RequestMapping("/api/team_position")
public class TeamPositionController {

    @Autowired
    TeamPositionService teamPositionService;
    @Autowired
    TeamService teamService;

    /**
     * Método que maneja las peticiones para obtener todas las posiciones de un equipo.
     * @param teamId Identificador del equipo del cual se quieren obtener las posiciones.
     * @return Una lista con todas las posiciones del equipo especificado.
     */
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
    
    /**
     * Método que maneja las peticiones para crear una posicion de equipo.
     * @param teamId Identificador del equipo al que se va a agregar la posición.
     * @param name Nombre de la nueva posición.
     * @return El objeto de la nueva posición creada.
     */
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

    /**
     * Método que maneja las peticiones de eliminación de una posición de equipo.
     * @param teamPositionId Identificador de la posición a eliminar.
     * @return El identificador de la posición eliminada.
     */
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
