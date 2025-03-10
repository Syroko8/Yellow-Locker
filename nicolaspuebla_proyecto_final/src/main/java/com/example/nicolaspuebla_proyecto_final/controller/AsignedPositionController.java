package com.example.nicolaspuebla_proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nicolaspuebla_proyecto_final.model.AsignedPosition;
import com.example.nicolaspuebla_proyecto_final.model.AsignedPositionPK;
import com.example.nicolaspuebla_proyecto_final.service.AsignedPositionService;


@Controller
@RequestMapping("/asigned_position")
public class AsignedPositionController {

    @Autowired
    private AsignedPositionService asignedPositionService;

    @GetMapping("/{id}")
    public ResponseEntity<AsignedPosition> getAsignedPosition(@PathVariable AsignedPositionPK id) {
        try {
            AsignedPosition position = asignedPositionService.getAsignedPosition(id);
            return ResponseEntity.ok().body(position);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<AsignedPosition> createAsignedPosition(@RequestBody AsignedPosition newAsignedPosition) {
        try {
            AsignedPosition position = asignedPositionService.createAsignedPosition(newAsignedPosition);
            return ResponseEntity.ok().body(position);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    


    


    
    


}

/*
 * 
 * 
 * @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        try {
            // Obtebemos y devolvemos la lista de productos.
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok().body(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }
 * 
 */