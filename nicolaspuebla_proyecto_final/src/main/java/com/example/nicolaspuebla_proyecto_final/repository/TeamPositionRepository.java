package com.example.nicolaspuebla_proyecto_final.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamPosition;

/**
 * Repositorio que interactúa con la base de datos manejando las posiciones en los equipos.
 */
@Repository
public interface TeamPositionRepository extends JpaRepository<TeamPosition, Long> {

    /**
     * Método que busca las posiciones de un equipo específico.
     * @param team El equipo del cual se buscan las posiciones.
     * @return Lista de posiciones del equipo.
     */
    @Query("SELECT tp FROM TeamPosition tp WHERE tp.team = ?1")
    List<TeamPosition> findTeamPositions(Team team);
}
