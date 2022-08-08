package board.boardspring;

import board.boardspring.domain.entitiy.Role;
import board.boardspring.domain.entitiy.User;
import board.boardspring.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class BoardSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.createUser(new User(1L,"wltnwkd241@naver.com","1234","choi",new ArrayList<>()));
			userService.addRoleToUser("wltnwkd241@naver.com","ROLE_USER");
		};
	}

}
