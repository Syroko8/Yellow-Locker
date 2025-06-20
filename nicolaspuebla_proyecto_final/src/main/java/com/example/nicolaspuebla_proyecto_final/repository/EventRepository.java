package com.example.nicolaspuebla_proyecto_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.nicolaspuebla_proyecto_final.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select u from Event where u.team_id = ?1")
    List<Event> findTeamEvents(Long team_id);
}
