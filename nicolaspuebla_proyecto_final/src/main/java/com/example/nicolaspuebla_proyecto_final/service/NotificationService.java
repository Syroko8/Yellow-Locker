package com.example.nicolaspuebla_proyecto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nicolaspuebla_proyecto_final.model.Notification;
import com.example.nicolaspuebla_proyecto_final.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public List<Notification> getUserNotifications(Long id){
        return notificationRepository.findUserNotifications(id);
    }

    public Notification createNotification(Notification newNotification){
        return notificationRepository.save(newNotification);
    }

    public Notification updateNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id){
        notificationRepository.deleteById(id);
    }
}
