package com.example.nicolaspuebla_proyecto_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NicolaspueblaProyectoFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(NicolaspueblaProyectoFinalApplication.class, args);	
	}

/* 
	@Bean
    public CommandLineRunner init(TeamService ts, UserService rs, LocalityService ls) {
        return args -> {
            Locality locality = new Locality("dede");
            User user = new User("Alberto", "Camacho", "dede", "dwdw", false);
            Team team = new Team("dedede", locality, "dwdw", "dwdw", "dwdwdwd");

			ls.createLocality(locality);
            ts.createTeam(team);
            rs.createUser(user);

        };
    }
*/
}
