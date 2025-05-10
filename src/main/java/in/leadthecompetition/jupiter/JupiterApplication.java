package in.leadthecompetition.jupiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JupiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(JupiterApplication.class, args);
	}

}
