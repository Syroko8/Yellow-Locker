package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Locality;

/**
 * Repositorio que interactúa con la base de datos manejando las localidades.
 */
@Repository
public interface LocalityRepository extends JpaRepository<Locality, Long> {

}
