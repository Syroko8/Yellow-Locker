package com.example.nicolaspuebla_proyecto_final.repository;

import com.example.nicolaspuebla_proyecto_final.model.Team;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
