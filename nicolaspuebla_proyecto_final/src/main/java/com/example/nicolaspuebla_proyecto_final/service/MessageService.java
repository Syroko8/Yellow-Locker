package com.example.nicolaspuebla_proyecto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nicolaspuebla_proyecto_final.model.Message;
import com.example.nicolaspuebla_proyecto_final.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    
    public Message getMessage(Long id){
        return messageRepository.getReferenceById(id);
    }

    public List<Message> getTeamMessages(Long team_id){
        return messageRepository.findTeamMessages(team_id);
    }

    public Message createMessage(Message newMessage){
        return messageRepository.save(newMessage);
    }   

    public Message updateMessage(Message message){
        return messageRepository.save(message);
    }

    public void deleteMessage(Long id){
        messageRepository.deleteById(id);
    }

}
