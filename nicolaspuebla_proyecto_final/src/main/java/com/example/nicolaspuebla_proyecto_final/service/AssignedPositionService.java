package com.example.nicolaspuebla_proyecto_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPosition;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPositionPK;
import com.example.nicolaspuebla_proyecto_final.repository.AssignedPositionRepository;

/**
 * Servicio que maneja las posiciones asignadas a los usuarios en los equipos.
 */
@Service
public class AssignedPositionService {

    @Autowired
    private AssignedPositionRepository asignedPositionRepository;

    /**
     * Obtiene una posición asignada por su ID.
     * @param id Identificador de la posición asignada.
     * @return La posición asignada correspondiente al ID, o null si no existe.
     */
    public AssignedPosition getAssignedPosition(AssignedPositionPK id){
        return asignedPositionRepository.findById(id)
        .orElse(null);
    }

    /**
     * Crea una nueva posición asignada.
     * @param newAsignedPosition La nueva posición asignada a crear.
     * @return La posición asignada creada.
     */
    public AssignedPosition createAssignedPosition(AssignedPosition newAsignedPosition){
        return asignedPositionRepository.save(newAsignedPosition);
    }

    /**
     * Actualiza una posición asignada existente.
     * @param newAsignedPosition La posición asignada con los nuevos datos.
     * @return La posición asignada actualizada.
     */
    public AssignedPosition updaAssignedPosition(AssignedPosition newAsignedPosition){
        return asignedPositionRepository.save(newAsignedPosition);
    }

    /**
     * Elimina una posición asignada.
     * @param asignedPosition La posición asignada a eliminar.
     */
    public void deleteAssignedPosition(AssignedPosition asignedPosition) throws Exception {
        try {
            asignedPositionRepository.delete(asignedPosition);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Elimina una posición asignada por el ID del usuario y el ID del equipo.
     * @param userId ID del usuario.
     * @param teamId ID del equipo.
     */
    public void deleteAssignedPosition(Long userId, Long teamId) {
        AssignedPositionPK id = new AssignedPositionPK(userId, teamId);
        asignedPositionRepository.deleteById(id);
    }

}
