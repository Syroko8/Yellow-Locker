package com.example.nicolaspuebla_proyecto_final.model.dto;

import java.util.List;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Locality;

/**
 * Clase que representa la respuesta para la ista de localidades.
 */
public class LocalityListResponse {
    private List<Locality> localities;

    public LocalityListResponse() {}

    public LocalityListResponse(List<Locality> localities) {
        this.localities = localities;
    }

    public List<Locality> getLocalities() {
        return localities;
    }

    public void setLocalities(List<Locality> localities) {
        this.localities = localities;
    }
}
