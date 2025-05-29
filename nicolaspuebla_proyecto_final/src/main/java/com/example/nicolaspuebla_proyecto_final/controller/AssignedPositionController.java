package com.example.nicolaspuebla_proyecto_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPosition;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.AssignedPositionPK;
import com.example.nicolaspuebla_proyecto_final.service.AssignedPositionService;

@RestController
@RequestMapping("/api/asigned_position")
public class AssignedPositionController {

    @Autowired
    private AssignedPositionService assignedPositionService;

    @GetMapping("/{id}")
    public ResponseEntity<AssignedPosition> getAssignedPosition(@PathVariable AssignedPositionPK id) {
        try {
            AssignedPosition position = assignedPositionService.getAssignedPosition(id);
            return ResponseEntity.ok().body(position);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<AssignedPosition> createAssignedPosition(@RequestBody AssignedPosition newAssignedPosition) {
        try {
            AssignedPosition position = assignedPositionService.createAssignedPosition(newAssignedPosition);
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