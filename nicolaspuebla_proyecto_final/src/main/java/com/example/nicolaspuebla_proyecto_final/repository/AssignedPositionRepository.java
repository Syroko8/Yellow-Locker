package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPosition;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPositionPK;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.User;

/**
 * Repositorio que interactúa con la base de datos manejando las posiciones asignadas a los usuarios en un equipo.
 */
@Repository
public interface AssignedPositionRepository extends JpaRepository<AssignedPosition, AssignedPositionPK> {

    /**
     * Método que busca una posición asignada por el identificador de usuario y equipo.
     * @param user El usuario al que se le asignó la posición.
     * @param team El equipo al que pertenece la posición.
     * @return La posición asignada correspondiente al usuario y equipo.
     */
    @Query("SELECT ap from AssignedPosition ap where ap.user = ?1 and ap.team = ?2")
    AssignedPosition findAssignedPositionByUserAndTeam(User user, Team team);
}
