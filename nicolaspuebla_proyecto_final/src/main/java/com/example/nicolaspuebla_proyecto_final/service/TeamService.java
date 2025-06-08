package com.example.nicolaspuebla_proyecto_final.service;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRolHolder;
import com.example.nicolaspuebla_proyecto_final.repository.TeamRepository;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.MobileUser;
import jakarta.persistence.NoResultException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nicolaspuebla_proyecto_final.repository.UserRepository;;

/*
 * Servicio que maneja las operaciones relacionadas con los equipos.
 */
@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Obtiene todos los equipos.
     * @return Lista de todos los equipos.
     */
    public List<Team> getTeams(){
        return teamRepository.findAll();
    }

    /**
     * Obtiene un equipo por su ID.
     * @param id Identificador del equipo.
     * @return El equipo correspondiente al ID.
     */
    public Team getTeam(Long id){
        return teamRepository.findById(id)
        .orElseThrow(() -> new NoResultException());
    }

    /**
     * Crea un nuevo equipo.
     * @param newTeam El nuevo equipo a crear.
     * @return El equipo creado.
     */
    public Team createTeam(Team newTeam){
        return teamRepository.save(newTeam);
    }

    /**
     * Actualiza un equipo existente.
     * @param team El equipo con los nuevos datos.
     * @return El equipo actualizado.
     */
    public Team updateTeam(Team team){
        return teamRepository.save(team);
    }

    /**
     * Elimina un equipo por su objeto.
     * @param team El equipo a eliminar.
     */
    public void deleteTeam(Team team){
        teamRepository.delete(team);
    }

    /**
     * Añade un usuario a un equipo.
     * @param teamId Identificador del equipo al que se añadirá el usuario.
     * @param userId Identificador del usuario que se añadirá al equipo.
     * @return Un objeto TeamRolHolder que contiene el usuario y el equipo al que se ha añadido.
     */
    public TeamRolHolder addUser(Long teamId, Long userId) throws Exception {
        try {
            Team team = teamRepository.findById(teamId)
            .orElseThrow(() -> new NoResultException());

            MobileUser user = (MobileUser) userRepository.findById(userId)
            .orElseThrow(() -> new NoResultException());
           
            team.addMember(user);
            teamRepository.save(team);
            
            return new TeamRolHolder(user, team);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Elimina un usuario de un equipo.
     * @param team El equipo del cual se eliminará al usuario.
     * @param user El usuario que se eliminará del equipo.
     */
    public void removeUser(Team team, MobileUser user)  throws Exception{
        try {
            team.removeMember(user);
            teamRepository.save(team);
            userRepository.save(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Obtiene un equipo junto con sus miembros.
     * @param teamId Identificador del equipo.
     * @return El equipo con sus miembros.
     */
    public Team getTeamWithMembers(Long teamId) {
        return teamRepository.findTeamWithMembers(teamId);
    }

    /**
     * Obtiene los nombres de todos los equipos.
     * @return Lista de nombres de equipos.
     */
    public List<String> getTeamNames() throws Exception{
        try {
            return teamRepository.getTeamNames();
        } catch (NoResultException nre) {
            throw new NoResultException(nre.getMessage());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}