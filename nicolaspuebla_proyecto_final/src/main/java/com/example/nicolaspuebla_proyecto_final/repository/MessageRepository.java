package com.example.nicolaspuebla_proyecto_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.nicolaspuebla_proyecto_final.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select u from Message where u.team_id = ?1")
    List<Message> findTeamMessages(Long team_id);
}
