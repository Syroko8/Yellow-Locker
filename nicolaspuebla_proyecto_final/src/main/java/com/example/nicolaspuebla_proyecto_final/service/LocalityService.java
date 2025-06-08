package com.example.nicolaspuebla_proyecto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Locality;
import com.example.nicolaspuebla_proyecto_final.repository.LocalityRepository;

/**
 * Servicio que maneja las localidades en la aplicación.
 */
@Service
public class LocalityService {

    @Autowired
    private LocalityRepository localityRepository;

    /**
     * Obtiene una localidad por su ID.
     * @param id Identificador de la localidad.
     * @return La localidad correspondiente al ID.
     */
    public Locality getLocality(Long id){
        return localityRepository.getReferenceById(id);
    }

    /**
     * Obtiene todas las localidades.
     * @return Lista de todas las localidades.
     */
    public List<Locality> getAllLocalities() throws Exception{
        try {
            return localityRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Crea una nueva localidad.
     * @param newLocality La nueva localidad a crear.
     * @return La localidad creada.
     */
    public Locality createLocality(Locality newLocality){
        return localityRepository.save(newLocality);
    }   

    /**
     * Actualiza una localidad existente.
     * @param locality La localidad con los nuevos datos.
     * @return La localidad actualizada.
     */
    public Locality updateLocality(Locality locality){
        return localityRepository.save(locality);
    }

    /**
     * Elimina una localidad por su ID.
     * @param id Identificador de la localidad a eliminar.
     */
    public void deleteLocality(Long id){
        localityRepository.deleteById(id);
    }
}
