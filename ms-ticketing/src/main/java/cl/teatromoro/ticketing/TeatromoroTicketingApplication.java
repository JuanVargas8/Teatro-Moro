package cl.teatromoro.ticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TeatromoroTicketingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatromoroTicketingApplication.class, args);
	}

}
