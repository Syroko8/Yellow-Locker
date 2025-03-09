package com.example.nicolaspuebla_proyecto_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nicolaspuebla_proyecto_final.model.Locality;
import com.example.nicolaspuebla_proyecto_final.repository.LocalityRepository;

@Service
public class LocalityService {

    @Autowired
    LocalityRepository localityRepository;

    public Locality getLocality(Long id){
        return localityRepository.getReferenceById(id);
    }

    public List<Locality> getAllLocalities(){
        return localityRepository.findAll();
    }

    public Locality createLocality(Locality newLocality){
        return localityRepository.save(newLocality);
    }   

    public Locality updateLocality(Locality locality){
        return localityRepository.save(locality);
    }

    public void deleteLocality(Long id){
        localityRepository.deleteById(id);
    }
}
