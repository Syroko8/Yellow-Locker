package com.example.nicolaspuebla_proyecto_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamPosition;

@Repository
public interface TeamPositionRepository extends JpaRepository<TeamPosition, Long> {

    @Query("SELECT tp FROM TeamPosition tp WHERE tp.team = ?1")
    List<TeamPosition> findTeamPositions(Team team);
}
