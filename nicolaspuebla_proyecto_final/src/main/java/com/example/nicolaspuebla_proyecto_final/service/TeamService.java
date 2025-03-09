package com.example.nicolaspuebla_proyecto_final.service;

import com.example.nicolaspuebla_proyecto_final.model.Team;
import com.example.nicolaspuebla_proyecto_final.repository.TeamRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public List<Team> getTeams(){
        return teamRepository.findAll();
    }

    public Team getTeam(Long id){
        teamRepository.findById(id);
        return null;
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
}