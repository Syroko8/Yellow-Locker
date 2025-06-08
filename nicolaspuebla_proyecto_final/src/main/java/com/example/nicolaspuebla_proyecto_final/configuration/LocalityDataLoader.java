package com.example.nicolaspuebla_proyecto_final.configuration;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Locality;
import com.example.nicolaspuebla_proyecto_final.service.LocalityService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase para la carga de las localidades en la base de datos al inicial el microservicio.
 */
@Configuration
public class LocalityDataLoader {

    @Autowired LocalityService localityService;

    @Bean
    public ApplicationRunner loadLocalities(){
        return args -> {

            if(!localityService.getAllLocalities().isEmpty()){
                return;
            }
            
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = new ClassPathResource("localities/localities.json").getInputStream();
            List<String> names = mapper.readValue(is, new TypeReference<List<String>>() {});

            for (String name : names) {
                localityService.createLocality(new Locality(name));
            }
        };
    }
}
