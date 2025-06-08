package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.stereotype.Repository;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositorio que interactúa con la base de datos manejando los equipos.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    /**
     * Método que obtiene un equipo con sus miembros.
     * @param teamId Identificador del equipo que se busca.
     * @return El equipo con sus miembros correspondientes.
     */
    @Query("SELECT t FROM Team t LEFT JOIN FETCH t.members WHERE t.id = :teamId")
    Team findTeamWithMembers(Long teamId);

    /**
     * Método que obtiene los nombres de todos los equipos.
     * @return Lista de nombres de equipos.
     */
    @Query("SELECT name FROM Team")
    List<String> getTeamNames();
}
