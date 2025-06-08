package com.example.nicolaspuebla_proyecto_final.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRol;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRolHolder;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRolPK;
import com.example.nicolaspuebla_proyecto_final.model.dto.MemberListElement;
import com.example.nicolaspuebla_proyecto_final.model.dto.TeamCreation;
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
import com.example.nicolaspuebla_proyecto_final.service.AssignedPositionService;
import com.example.nicolaspuebla_proyecto_final.service.EventService;
import com.example.nicolaspuebla_proyecto_final.service.TeamRolService;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Player;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPosition;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPositionPK;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Captain;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Coach;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controlador que maneja las peticiones relacionadas con la entidad Team.
 */
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
    @Autowired
    AssignedPositionService assignedPositionService;

    /**
     * Método que maneja las peticiones para obtener un equipo por su ID.
     * @param id Identificador del equipo.
     * @return El objeto con la información del equipo solicitado.
     */
   @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable long id) {
        try {
            Team team = teamService.getTeam(id);
             team.setEventList(eventService.getTeamEvents(team));
            return ResponseEntity.ok().body(team);
        } catch (NoResultException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Método que maneja las peteticiones para obtener un equipo con información extendida.
     * @param id Identificador del equipo.
     * @return Un objeto con el equipo y una lista de eventos asociados.
     */
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

    /**
     * Método que maneja las peticiones para obtener todos los equipos.
     * @return Una lisra con todos los equipos registrados.
     */
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

    /**
     * Método que maneja las peticiones para unirse a un equipo.
     * @param teamId Identificador del equipo al que se quiere unir.
     * @param userId Identificador del usuario que quiere unirse al equipo.
     * @return El objeto del usuario que se ha unido al equipo.
     */
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

    /**
     * Método que maneja las peticiones para unirse a un equipo como capitán.
     * @param teamId Identificador del equipo al que se va a unir.
     * @param userId Identificador del usuario que se va a unir como capitán.
     * @return El objeto del usuario que se ha unido al equipo.
     */
    @PostMapping("/join_cap")
    public ResponseEntity<MobileUser> joinTeamCap(@RequestParam Long teamId, @RequestParam Long userId) {
        try {
            TeamRolHolder trh = teamService.addUser(teamId, userId);
            teamRolService.createTeamRol(new Captain(trh.getUser(), trh.getTeam()));
            return ResponseEntity.ok().body(trh.getUser());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }   

    /**
     * Método que maneja las peticiones para obtener los miembros de un equipo con sus roles.
     * @param id Identificador del equipo del que se quieren obtener los miembros.
     * @return Una lista con los miembros del equipo y sus roles.
     */
    @GetMapping("/members/{id}")
    public ResponseEntity<List<MemberListElement>> getTeamMembersWithRoles(@PathVariable Long id) {
        try {
            Team team = teamService.getTeam(id);
            ArrayList<MemberListElement> members = new ArrayList<>();

            for(TeamRol role : team.getTeamRoles()){
                String rolType = "";

                if(role instanceof Player){
                    rolType = "Player";
                } else if(role instanceof Coach){
                    rolType = "Coach";
                } else {
                    rolType = "Captain";
                }

                members.add(new MemberListElement(rolType, ( MobileUser)userService.getUser(role.getId().getUserId())));
            }
            return ResponseEntity.ok().body(members);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }     
    }

    /**
     * Método que maneja las peticiones para obtener los nombres de los equipos registrados.
     * @return Una lista con los nombres de todos los equipos.
     */
    @GetMapping("/names")
    public ResponseEntity<TeamNameListResponse> getTeamNames() {
        try {
            List<String> names = teamService.getTeamNames(); 
            return ResponseEntity.ok().body(new TeamNameListResponse(names));  
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);   
        }
    }

    /**
     * Método que maneja las peticiones para crear un nuevo equipo.
     * @param teamCreation Objeto con la información necesaria para crear un equipo.
     * @return El objeto del equipo creado.
     */
    @PostMapping("/create")
    public ResponseEntity<Team> createTeam(@RequestBody TeamCreation teamCreation) {
        try {
            MobileUser creator = (MobileUser) userService.getUser(teamCreation.getUserId());

            Team newTeam = new Team(
                teamCreation.getTeamName(), 
                teamCreation.getTeamLocality(), 
                "logo", 
                "chatKey", 
                teamCreation.getTeamSport()
            ); 
            
            Team savedTeam = teamService.createTeam(newTeam);
            savedTeam.addMember(creator);
            Captain newCreatorRol = new Captain(creator, savedTeam); 
            teamRolService.createTeamRol(newCreatorRol);

            return ResponseEntity.ok().body(savedTeam);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);   
        }    
    }

    /**
     * Método que maneja las peticiones para el abandono de un equipo.
     * @param userId Identificador del usuario que desea abandonar el equipo.
     * @param teamId Identificador del equipo del que se desea abandonar.
     * @return El objeto del equipo del que se ha abandonado.
     */
    @PostMapping("/leave")
    public ResponseEntity<Team> leaveTeam(@RequestParam Long userId, @RequestParam Long teamId) {
        try {
            MobileUser user = (MobileUser) userService.getUser(userId);
            Team team = teamService.getTeam(teamId);

            teamService.removeUser(team, user);
            TeamRol role = teamRolService.getTeamRol(new TeamRolPK(user.getId(), team.getId()));
            teamRolService.removeRol(role);

            AssignedPosition assignedPosition = assignedPositionService.getAssignedPosition(new AssignedPositionPK(
                userId,
                teamId
            ));
            if(assignedPosition != null){
                assignedPositionService.deleteAssignedPosition(assignedPosition);
            }
        
            return ResponseEntity.ok().body(team);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}