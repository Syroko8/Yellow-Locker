package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nicolaspuebla_proyecto_final.model.AsignedPosition;
import com.example.nicolaspuebla_proyecto_final.model.AsignedPositionPK;

@Repository
public interface AsignedPositionRepository extends JpaRepository<AsignedPosition, AsignedPositionPK> {

    @Query("select u from AsignedPosition where u.user_id = ?1 and u.team_id = ?2")
    AsignedPosition findByUserAndTeam(Long user_id, Long team_id);
}
