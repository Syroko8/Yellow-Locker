package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRol;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRolPK;

@Repository
public interface TeamRolRepository extends JpaRepository<TeamRol, TeamRolPK> {

}
