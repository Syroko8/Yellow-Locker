package com.example.nicolaspuebla_proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

/**
 * Controlador que recibe las peticiones relacionadas con la entidad AssignedPosition.
 */
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

    /**
     * Endpoint que recibe las peticiones para obtener información sobre una posición asignada.
     * @param userId Identificador del usuario al que está asignada la posición.
     * @param teamId Identificador del equipo en el que está asignada la posición.
     * @return El objeto con la información de la posición.
     */
    @GetMapping()
    public ResponseEntity<AssignedPosition> getAssignedPosition(@RequestParam Long userId, @RequestParam Long teamId) {
        try {
            AssignedPosition position = assignedPositionService.getAssignedPosition(new AssignedPositionPK(userId, teamId));
            return ResponseEntity.ok().body(position);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Función que recibe las peticiones de creación de posiciones asignadas, a la hora de asignar un usuario a una posición en un equipo.
     * @param newAssignedPosition Un objeto AssignedPosition que contiene la información de la posición asignada a crear.
     * @return El objeto con la información de la posición asignada creada.
     */
    @PostMapping("/create")
    public ResponseEntity<AssignedPosition> createAssignedPosition(@RequestBody AssignedPosition newAssignedPosition) {
        try {
            AssignedPosition position = assignedPositionService.createAssignedPosition(newAssignedPosition);
            return ResponseEntity.ok().body(position);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Función que recibe las peticiones de cambio de posiciones asignadas, a la hora de cambiar la posición de un usuario en un equipo.
     * @param userId Identificador del usuario al que está asignada la posición.
     * @param teamId Identificador del equipo en el que está asignada la posición.
     * @param teamPositionId Identificador de la nueva posición del equipo.
     * @return El objeto con la nueva información de la posición asignada.
     */
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