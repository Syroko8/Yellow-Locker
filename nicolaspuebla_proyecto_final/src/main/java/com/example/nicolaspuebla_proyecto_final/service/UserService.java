package com.example.nicolaspuebla_proyecto_final.service;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.User;
import com.example.nicolaspuebla_proyecto_final.repository.UserRepository;
import jakarta.persistence.NoResultException;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que maneja las operaciones relacionadas con los usuarios.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Obtiene un usuario por su ID.
     * @param id Identificador del usuario.
     * @return El usuario correspondiente al ID.
     */
    public User getUser(Long id) throws NoResultException, NoSuchElementException{
        return userRepository.findById(id)
        .orElseThrow(() -> new NoResultException());
    }

    /**
     * Obtiene un usuario por su correo electrónico.
     * @param email Correo electrónico del usuario.
     * @return El usuario correspondiente al correo electrónico.
     */
    public User getUserByEmail(String email) throws NoResultException{
        try {
            System.out.println("Getting user by email: " + email);
            User user = userRepository.findUserByEmail(email);
            return user;
        } catch (NoResultException e) {
            throw new NoResultException();
        } 
    }

    /**
     * Crea un nuevo usuario.
     * @param newUser El nuevo usuario a crear.
     * @return El usuario creado.
     */
    public User createUser(User newUser){
        return userRepository.save(newUser);
    }

    /**
     * Actualiza un usuario existente.
     * @param user El usuario con los nuevos datos.
     * @return El usuario actualizado.
     */
    public User updateUser(User user){
        return userRepository.save(user);
    }

    /**
     * Elimina un usuario.
     * @param user El usuario a eliminar.
     */
    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
