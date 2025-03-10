package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nicolaspuebla_proyecto_final.model.AsignedPosition;
import com.example.nicolaspuebla_proyecto_final.model.AsignedPositionPK;

@Repository
public interface AsignedPositionRepository extends JpaRepository<AsignedPosition, AsignedPositionPK> {

}
