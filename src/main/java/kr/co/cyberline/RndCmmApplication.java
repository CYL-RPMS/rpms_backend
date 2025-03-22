package kr.co.cyberline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class RndCmmApplication {

	public static void main(String[] args) {
		try {
		SpringApplication.run(RndCmmApplication.class, args);
		} catch (Exception e) {
			System.err.println("An error occurred while starting the application:");
			e.printStackTrace();
			System.exit(1);
		}
	}

}
