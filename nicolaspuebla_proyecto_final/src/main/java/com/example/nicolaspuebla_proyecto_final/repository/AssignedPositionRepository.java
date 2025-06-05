package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPosition;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPositionPK;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.User;

@Repository
public interface AssignedPositionRepository extends JpaRepository<AssignedPosition, AssignedPositionPK> {

    @Query("SELECT ap from AssignedPosition ap where ap.user = ?1 and ap.team = ?2")
    AssignedPosition findAssignedPositionByUserAndTeam(User user, Team team);
}
