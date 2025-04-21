package com.example.nicolaspuebla_proyecto_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/* 
import org.springframework.context.annotation.Bean;
import java.sql.Date;
import org.springframework.boot.CommandLineRunner;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Locality;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Message;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.MobileUser;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.User;
import com.example.nicolaspuebla_proyecto_final.service.LocalityService;
import com.example.nicolaspuebla_proyecto_final.service.TeamService;
import com.example.nicolaspuebla_proyecto_final.service.UserService;
import com.example.nicolaspuebla_proyecto_final.service.MessageService;
*/
@SpringBootApplication
public class NicolaspueblaProyectoFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(NicolaspueblaProyectoFinalApplication.class, args);	
	}

 /* 
 	@Bean
    public CommandLineRunner init(TeamService ts, UserService rs, LocalityService ls, MessageService messageService) {
        return args -> {
            Locality locality = new Locality("dede");
            MobileUser mobileUser = new MobileUser("Fernando", "Martinez", "deda", "dwdw", false, Date.valueOf("2000-05-07"));
            Team team = new Team("Team1", locality, "logo", "chatkey", "Football");
            Message message = new Message(mobileUser, team);
			ls.createLocality(locality);
            rs.createUser(mobileUser);
            ts.createTeam(team);
            messageService.createMessage(message);
        };
    }*/

}
