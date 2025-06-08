package com.example.nicolaspuebla_proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.example.nicolaspuebla_proyecto_final.model.dto.LocalityListResponse;
import com.example.nicolaspuebla_proyecto_final.service.LocalityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador que maneja las peticiones correspondientes a la entidad Locality.
 */
@Controller
@RequestMapping("api/locality")
public class LocalityController {

    @Autowired
    LocalityService localityService;

    /**
     * Método que maneja las peticiones para obtener todas las localidades.
     * @return Una lista con todas las localidades existentes.
     */
    @GetMapping()
    public ResponseEntity<LocalityListResponse> getLocalities() {
        try {
            return ResponseEntity.ok().body(new LocalityListResponse(localityService.getAllLocalities()));   
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
