package com.example.nicolaspuebla_proyecto_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nicolaspuebla_proyecto_final.repository.TeamRepository;
import com.example.nicolaspuebla_proyecto_final.repository.TeamRolRepository;
import com.example.nicolaspuebla_proyecto_final.repository.UserRepository;
import jakarta.persistence.NoResultException;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.MobileUser;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRol;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRolPK;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Coach;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Captain;

/**
 * Servicio que maneja los roles de los usuarios en los equipos.
 */
@Service
public class TeamRolService {

    @Autowired
    TeamRolRepository teamRolRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired 
    UserRepository userRepository;

    /**
     * Crea un nuevo rol de equipo.
     * @param teamRol El rol de equipo a crear.
     * @throws Exception Si ocurre un error al crear el rol.
     */
    public void createTeamRol(TeamRol teamRol) throws Exception{
        try{
            teamRolRepository.save(teamRol);
            MobileUser mobileUser = teamRol.getUser().addRol(teamRol);
            Team team = teamRol.getTeam().addRol(teamRol);
            userRepository.save(mobileUser);
            teamRepository.save(team);
        } catch(Exception e){
            System.err.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Obtiene un rol de equipo por su clave primaria.
     * @param teamRolPK La clave primaria del rol de equipo.
     * @return El rol de equipo correspondiente a la clave primaria.
     */
    public TeamRol getTeamRol(TeamRolPK teamRolPK) throws Exception{
        try{
            Team team = teamRepository.findById(teamRolPK.getTeamId())
            .orElseThrow(() -> new NoResultException());

            MobileUser user = (MobileUser) userRepository.findById(teamRolPK.getUserId())
            .orElseThrow(() -> new NoResultException());

            return teamRolRepository.findTeamRolByUserAndTeam(user, team);
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Actualiza un rol de equipo existente.
     * @param teamRol El rol de equipo con los nuevos datos.
     * @return El rol de equipo actualizado.
     * @throws Exception Si ocurre un error al actualizar el rol.
     */
    public TeamRol updateTeamRol(TeamRol teamRol) throws Exception{
        try{
            teamRolRepository.save(teamRol);
            return teamRol;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Obtiene el nivel de rol de un usuario en un equipo.
     * @param teamRolPK La clave primaria del rol de equipo.
     * @return El nivel de rol del usuario en el equipo.
     */
    public String getRolLevel(TeamRolPK teamRolPK) throws Exception{
        try {
            TeamRol tr = getTeamRol(teamRolPK);
        
            if(tr instanceof Captain){
                return "Captain";
            } else if(tr instanceof Coach){
                return "Coach";
            } else {
                return "Player";
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Elimina un rol de equipo.
     * @param role El rol de equipo a eliminar.
     */
    public void removeRol(TeamRol role) throws Exception {
        try {
            teamRolRepository.delete(role);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
