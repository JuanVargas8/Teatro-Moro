package cl.teatromoro.suscripciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@EnableFeignClients(basePackages = { "cl.teatromoro" })
@SpringBootApplication
@EnableKafka
public class TeatromoroSuscripcionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatromoroSuscripcionesApplication.class, args);
	}

}
