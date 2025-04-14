package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.stereotype.Repository;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
