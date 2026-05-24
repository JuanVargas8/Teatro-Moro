package cl.teatromoro.reserva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TeatromoroReservaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatromoroReservaApplication.class, args);
	}

}
