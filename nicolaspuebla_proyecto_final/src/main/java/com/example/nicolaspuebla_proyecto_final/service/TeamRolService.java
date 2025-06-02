package com.example.nicolaspuebla_proyecto_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nicolaspuebla_proyecto_final.repository.TeamRepository;
import com.example.nicolaspuebla_proyecto_final.repository.TeamRolRepository;
import com.example.nicolaspuebla_proyecto_final.repository.UserRepository;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.MobileUser;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRol;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRolPK;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Coach;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Captain;

@Service
public class TeamRolService {

    @Autowired
    TeamRolRepository teamRolRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired 
    UserRepository userRepository;

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

    public TeamRol getTeamRol(TeamRolPK teamRolPK) throws Exception{
        try{
            Team team = teamRepository.findById(teamRolPK.getTeamId())
            .orElseThrow(() -> new Error());

            MobileUser user = (MobileUser) userRepository.findById(teamRolPK.getUserId())
            .orElseThrow(() -> new Error());

            return teamRolRepository.findTeamRolByUserAndTeam(user, team);
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public TeamRol updateTeamRol(TeamRol teamRol) throws Exception{
        try{
            teamRolRepository.save(teamRol);
            return teamRol;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

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

    public void removeRol(TeamRol role) throws Exception {
        try {
            teamRolRepository.delete(role);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
