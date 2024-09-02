package be.iccbxl.pid;

import be.iccbxl.pid.model.RoleRepository;
import be.iccbxl.pid.model.User;
import be.iccbxl.pid.model.UserRepository;
import be.iccbxl.pid.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservationsSpringBootApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ReservationsSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Create users
		User user1 = new User("admin", "admin", "admin");
		user1.setPassword("admin");
		user1.setEmail("john.doe@example.com");
		user1.setLangue("fr");
		user1.setRole("admin");

		User user2 = new User("user", "user", "user");
		user2.setPassword("user");
		user2.setEmail("jane.doe@example.com");
		user2.setLangue("fr");
		user2.setRole("member");

		if(!userRepository.existsByEmail(user1.getEmail()))
			userService.addUser(user1);
		if(!userRepository.existsByEmail(user2.getEmail()))
			userService.addUser(user2);
	}

}
