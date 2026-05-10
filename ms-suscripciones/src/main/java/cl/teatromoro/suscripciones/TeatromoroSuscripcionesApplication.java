package cl.teatromoro.suscripciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TeatromoroSuscripcionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatromoroSuscripcionesApplication.class, args);
	}

}
