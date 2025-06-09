package com.example.nicolaspuebla_proyecto_final.model.dto;

import java.util.List;

/**
 * Clase que representa la respuesta para la lista de nombres de equipos.
 */
public class TeamNameListResponse {
    private List<String> list;

    public TeamNameListResponse(){}

    public TeamNameListResponse(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }   
}
