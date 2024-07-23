package org.kernel360.busseat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BusSeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusSeatApplication.class, args);
	}

}
