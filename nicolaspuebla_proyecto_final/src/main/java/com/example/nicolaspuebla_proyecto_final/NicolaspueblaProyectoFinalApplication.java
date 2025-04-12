package com.example.nicolaspuebla_proyecto_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Locality;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.User;
import com.example.nicolaspuebla_proyecto_final.service.LocalityService;
import com.example.nicolaspuebla_proyecto_final.service.TeamService;
import com.example.nicolaspuebla_proyecto_final.service.UserService;

@SpringBootApplication
public class NicolaspueblaProyectoFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(NicolaspueblaProyectoFinalApplication.class, args);	
	}

 
	@Bean
    public CommandLineRunner init(TeamService ts, UserService rs, LocalityService ls) {
        return args -> {
            Locality locality = new Locality("dede");
            User user = new User("Fernando", "Martinez", "dede", "dwdw", false);
            Team team = new Team("dedede", locality, "dwdw", "dwdw", "Footbal");

			ls.createLocality(locality);
            ts.createTeam(team);
            rs.createUser(user);

        };
    }

}
