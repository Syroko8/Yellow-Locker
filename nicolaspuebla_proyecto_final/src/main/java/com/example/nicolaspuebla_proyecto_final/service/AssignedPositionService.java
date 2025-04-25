package com.example.nicolaspuebla_proyecto_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPosition;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPositionPK;
import com.example.nicolaspuebla_proyecto_final.repository.AssignedPositionRepository;

@Service
public class AssignedPositionService {

    @Autowired
    private AssignedPositionRepository asignedPositionRepository;

    public AssignedPosition getAssignedPosition(AssignedPositionPK id){
        return asignedPositionRepository.getReferenceById(id);
    }

    public AssignedPosition createAssignedPosition(AssignedPosition newAsignedPosition){
        return asignedPositionRepository.save(newAsignedPosition);
    }

    public AssignedPosition updaAssignedPosition(AssignedPosition newAsignedPosition){
        return asignedPositionRepository.save(newAsignedPosition);
    }

    public void deleteAssignedPosition(AssignedPosition asignedPosition){
        asignedPositionRepository.delete(asignedPosition);
    }
}
