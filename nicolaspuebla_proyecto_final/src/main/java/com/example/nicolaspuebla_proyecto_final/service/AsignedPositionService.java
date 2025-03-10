package com.example.nicolaspuebla_proyecto_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.nicolaspuebla_proyecto_final.model.AsignedPosition;
import com.example.nicolaspuebla_proyecto_final.model.AsignedPositionPK;
import com.example.nicolaspuebla_proyecto_final.repository.AsignedPositionRepository;

public class AsignedPositionService {

    @Autowired
    private AsignedPositionRepository asignedPositionRepository;

    public AsignedPosition getAsignedPosition(AsignedPositionPK id){
        return asignedPositionRepository.getReferenceById(id);
    }

    public AsignedPosition createAsignedPosition(AsignedPosition newAsignedPosition){
        return asignedPositionRepository.save(newAsignedPosition);
    }

    public AsignedPosition updaAsignedPosition(AsignedPosition newAsignedPosition){
        return asignedPositionRepository.save(newAsignedPosition);
    }

    public void deleteAsignedPosition(AsignedPosition asignedPosition){
        asignedPositionRepository.delete(asignedPosition);
    }
}
