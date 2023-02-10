package eu.asgardschmiede.todoliste;

import org.springframework.beans.factory.annotation.Autowired;
import eu.asgardschmiede.todoliste.model.User;
import eu.asgardschmiede.todoliste.model.UserRole;
import eu.asgardschmiede.todoliste.model.UserStatus;
import eu.asgardschmiede.todoliste.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TodolisteApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/* @Value("$(db.admin.user.reset")
     private boolean adminReset;
 */
	public static void main(String[] args) {
		SpringApplication.run(TodolisteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		User user = new User();
		user.setUsername("admin");
		user.setEmail("admin@shield.org");
		user.setPassword(passwordEncoder.encode("geheim123"));
		user.setRole(UserRole.ADMIN);
		user.setStatus(UserStatus.ACTIVE);
		userRepository.save(user);

		user = new User();
		user.setUsername("p.parker");
		user.setEmail("p.parker@shield.org");
		user.setPassword(passwordEncoder.encode("geheim123"));
		user.setRole(UserRole.USER);
		user.setStatus(UserStatus.ACTIVE);
		userRepository.save(user);

		user = new User();
		user.setUsername("b.banner");
		user.setEmail("b.banner@shield.org");
		user.setPassword(passwordEncoder.encode("geheim123"));
		user.setRole(UserRole.USER);
		user.setStatus(UserStatus.BLOCKED);
		userRepository.save(user);
	}
}
