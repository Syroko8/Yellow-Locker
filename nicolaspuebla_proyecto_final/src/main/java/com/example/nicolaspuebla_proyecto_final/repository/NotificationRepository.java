package com.example.nicolaspuebla_proyecto_final.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Notification;

/**
 * Repositorio que interactúa con la base de datos manejando las las notificaciones de los usuarios.
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * Métod que obtiene las notificaciones de un usuario específico.
     * @param user_id Identificador del usuario cuyas notificaciones se buscan.
     * @return Lista de notificaciones del usuario.
     */
    @Query("select n from Notification n where n.destinatary = ?1")
    List<Notification> findUserNotifications(Long user_id);
}
