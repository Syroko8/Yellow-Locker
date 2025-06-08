package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRol;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRolPK;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.User;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;

/**
 * Repositorio que interactúa con la base de datos manejando los roles asignados a los usuarios en los equipos.
 */
@Repository
public interface TeamRolRepository extends JpaRepository<TeamRol, TeamRolPK> {
        
    /**
     * Método que busca un rol por los identificadores de usuario y equipo.
     * @param user El usuario cuyo rol se busca.
     * @param team El equipo al cual pertenece el rol.
     * @return El rol del usuario en el equipo.
     */
    @Query("select tr from TeamRol tr where tr.user = ?1 and tr.team = ?2")
    TeamRol findTeamRolByUserAndTeam(User user, Team team);
}
