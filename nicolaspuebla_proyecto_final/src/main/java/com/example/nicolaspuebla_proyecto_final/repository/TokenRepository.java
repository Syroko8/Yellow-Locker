package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Token;

/**
 * Repositorio que interactúa con la base de datos manejando los tokens de los usuarios.
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    /**
     * Método que busca un token por su valor.
     * @param token El valor del token a buscar.
     * @return El token encontrado.
     */
    @Query("select t from Token t where t.token = ?1")
    Token getTokenByToken(String token);

    /**
     * Método que busca un token por el identificador del usuario.
     * @param userId El identificador del usuario cuyo token se busca.
     * @return El token asociado al usuario.
     */
    @Query("select t from Token t where t.user_id = ?1")
    Token getTokenByUserId(Long userId);

    /**
     * Método que elimina un token por su valor.
     * @param token El valor del token a eliminar.
     * @return El token eliminado.
     */
    @Query("delete from Token t where t.token = ?1")
    Token deleteByToken(String token);
}
