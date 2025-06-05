package com.example.nicolaspuebla_proyecto_final.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamPosition;
import com.example.nicolaspuebla_proyecto_final.repository.TeamPositionRepository;
import com.example.nicolaspuebla_proyecto_final.repository.TeamRepository;

import jakarta.persistence.NoResultException;

@Service
public class TeamPositionService {

    @Autowired
    TeamPositionRepository teamPositionRepository;
    @Autowired
    TeamRepository teamRepository;

    public TeamPosition getTeamPosition(Long id) throws Exception{
        try {
            return teamPositionRepository.findById(id)
            .orElseThrow(() -> new NoResultException());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public TeamPosition createTeamPosition(TeamPosition newTeamPosition) throws Exception {
        try {
            teamPositionRepository.save(newTeamPosition);
            return newTeamPosition;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteTeamPosition(Long id) throws Exception, SQLIntegrityConstraintViolationException {
        try {
            teamPositionRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            Throwable rootCause = e.getRootCause();

            if (rootCause instanceof java.sql.SQLException sqlEx) {
                int errorCode = sqlEx.getErrorCode();
            
                if (errorCode == 1451) {
                    throw new SQLIntegrityConstraintViolationException();
                } else {
                    throw new Exception("Error SQL " + errorCode + ": " + sqlEx.getMessage(), e);
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<TeamPosition> getTeamPositions(Team team) throws Exception {
        try {
            return teamPositionRepository.findTeamPositions(team);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
