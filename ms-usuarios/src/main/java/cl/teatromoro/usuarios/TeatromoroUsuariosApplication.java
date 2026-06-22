package cl.teatromoro.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@ComponentScan(basePackages = { "cl.teatromoro" })
public class TeatromoroUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatromoroUsuariosApplication.class, args);
	}

}
