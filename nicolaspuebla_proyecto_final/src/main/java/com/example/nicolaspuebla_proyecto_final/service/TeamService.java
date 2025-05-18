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

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Team> getTeams(){
        return teamRepository.findAll();
    }

    public Team getTeam(Long id){
        return teamRepository.findById(id)
        .orElseThrow(() -> new NoResultException());
    }

    public Team createTeam(Team newTeam){
        return teamRepository.save(newTeam);
    }

    public Team updateTeam(Team team){
        return teamRepository.save(team);
    }

    public void deleteTeam(Team team){
        teamRepository.delete(team);
    }

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

    public Team getTeamWithMembers(Long teamId) {
        return teamRepository.findTeamWithMembers(teamId);
    }

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