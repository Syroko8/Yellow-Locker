package com.example.nicolaspuebla_proyecto_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.destinatary_id = ?1")
    List<Notification> findUserNotifications(Long user_id);
}
