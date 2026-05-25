package cl.teatromoro.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class TeatromoroUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatromoroUsuariosApplication.class, args);
	}

}
