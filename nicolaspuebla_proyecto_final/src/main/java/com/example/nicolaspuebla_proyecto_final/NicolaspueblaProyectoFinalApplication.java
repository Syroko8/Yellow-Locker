package com.example.nicolaspuebla_proyecto_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/* 
import org.springframework.context.annotation.Bean;
import java.sql.Date;
import org.springframework.boot.CommandLineRunner;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Match;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Event;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Locality;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Message;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.MobileUser;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.service.EventService;
import com.example.nicolaspuebla_proyecto_final.service.LocalityService;
import com.example.nicolaspuebla_proyecto_final.service.TeamService;
import com.example.nicolaspuebla_proyecto_final.service.UserService;
import com.example.nicolaspuebla_proyecto_final.service.MessageService;
*/

/**
 * Clase principal del microservicio.
 * @author Nicolas Puebla Martín
 */
@SpringBootApplication
public class NicolaspueblaProyectoFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(NicolaspueblaProyectoFinalApplication.class, args);	
	}
  /* 
 	@Bean
    public CommandLineRunner init(TeamService ts, UserService rs, LocalityService ls, MessageService messageService, EventService eventService) {
        return args -> {
            Locality locality = new Locality("dede");
            MobileUser mobileUser = new MobileUser("Fernando", "Martinez", "deda", "d607c8855011631e3b002283685084375b179069fded6c16ed4e0c49a59a808d", false, Date.valueOf("2000-05-07"));
            Team team = new Team("Team1", locality, "logo", "chatkey", "Football");
            Team team2 = new Team("Team2", locality, "logo", "chatkey", "Volleyball");
            Event match1 = new Match(team, 0.0, 0.0,  "2025-04-15T10:15:00+00:00", team2, 0, 3);
            Event match2 = new Match(team, 0.0, 0.0, "2025-04-15T10:15:00+00:00", team2, 2, 1);
            Event match3 = new Match(team, 0.0, 0.0, "2025-04-15T10:15:00+00:00", team2, 2, 2);
            Message message = new Message(mobileUser, team);
			ls.createLocality(locality);
            rs.createUser(mobileUser);
            ts.createTeam(team);
            messageService.createMessage(message);
            ts.createTeam(team2);
            eventService.createEvent(match1);
            eventService.createEvent(match2);
            eventService.createEvent(match3);
        };
    }*/

}
