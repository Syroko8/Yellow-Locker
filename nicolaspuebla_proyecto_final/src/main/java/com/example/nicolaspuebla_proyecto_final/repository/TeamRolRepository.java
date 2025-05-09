package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRol;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRolPK;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.User;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;

@Repository
public interface TeamRolRepository extends JpaRepository<TeamRol, TeamRolPK> {
        
    @Query("select tr from TeamRol tr where tr.user = ?1 and tr.team = ?2")
    TeamRol findTeamRolByUserAndTeam(User user, Team team);
}
