package com.example.nicolaspuebla_proyecto_final.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Notification;
import com.example.nicolaspuebla_proyecto_final.repository.NotificationRepository;

/**
 * Servicio que maneja las notificaciones de los usuarios.
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * Obtene todas las notificaciones de un usuario.
     * @param id Identificador del usuario.
     * @return Lista de notificaciones del usuario.
     */
    public List<Notification> getUserNotifications(Long id){
        return notificationRepository.findUserNotifications(id);
    }

    /**
     * Crea una nueva notificación.
     * @param newNotification La nueva notificación a crear.
     * @return La notificación creada.
     */
    public Notification createNotification(Notification newNotification){
        return notificationRepository.save(newNotification);
    }

    /**
     * Actualiza una notificación existente.
     * @param notification La notificación con los nuevos datos.
     * @return La notificación actualizada.
     */
    public Notification updateNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    /**
     * Elimina una notificación por su ID.
     * @param id ID de la notificación a eliminar.
     */
    public void deleteNotification(Long id){
        notificationRepository.deleteById(id);
    }
}
