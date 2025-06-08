package com.example.nicolaspuebla_proyecto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Message;
import com.example.nicolaspuebla_proyecto_final.repository.MessageRepository;

/**
 * Servicio que maneja las operaciones relacionadas con los mensajes.
 */
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    
    /**
     * Obtiene un mensaje por su ID.
     * @return Información del mensaje.
     */
    public Message getMessage(Long id){
        return messageRepository.getReferenceById(id);
    }

    /**
     * Obtiene todos los mensajes de un equipo.
     * @param team_id ID del equipo del cual se desean obtener los mensajes.
     * @return Lista de todos los mensajes del equipo.
     */
    public List<Message> getTeamMessages(Long team_id){
        return messageRepository.findTeamMessages(team_id);
    }

    /**
     * Crea un nuevo mensaje.
     * @param newMessage El nuevo mensaje a crear.
     * @return El mensaje creado.
     */
    public Message createMessage(Message newMessage){
        return messageRepository.save(newMessage);
    }   

    /**
     * Actualiza un mensaje existente.
     * @param message El mensaje con los nuevos datos.
     * @return El mensaje actualizado.
     */
    public Message updateMessage(Message message){
        return messageRepository.save(message);
    }

    /**
     * Elimina un mensaje por su ID.
     * @param id ID del mensaje a eliminar.
     */
    public void deleteMessage(Long id){
        messageRepository.deleteById(id);
    }

}
