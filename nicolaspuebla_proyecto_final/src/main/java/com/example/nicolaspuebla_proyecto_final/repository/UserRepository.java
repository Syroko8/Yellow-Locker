package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.User;

/**
 * Repositorio que interactúa con la base de datos manejando los usuarios.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Método que busca un usuario por su correo electrónico.
     * @param userEmail El correo electrónico del usuario a buscar.
     * @return El usuario encontrado.
     */
    @Query("select u from User u where u.email = ?1")
    User findUserByEmail(String userEmail);
}
