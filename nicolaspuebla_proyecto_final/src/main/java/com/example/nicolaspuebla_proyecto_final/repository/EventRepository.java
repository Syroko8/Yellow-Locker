package com.example.nicolaspuebla_proyecto_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Event;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;

/**
 * Repositorio que interactúa con la base de datos manejando los eventos de los equipos.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Método que busca los eventos de un equipo específico.
     * @param team El equipo del cual se buscan los eventos.
     * @return Lista de eventos del equipo.
     */
    @Query("select e from Event e where e.team = ?1 or opponent = ?1")
    List<Event> findTeamEvents(Team team);
}
