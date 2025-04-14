package com.example.nicolaspuebla_proyecto_final.service;

import com.example.nicolaspuebla_proyecto_final.model.apiModels.LandingScreenTeams;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.repository.TeamRepository;

import jakarta.persistence.NoResultException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

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
}