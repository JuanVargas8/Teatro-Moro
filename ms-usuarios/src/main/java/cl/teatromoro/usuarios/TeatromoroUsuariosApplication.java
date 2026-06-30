package cl.teatromoro.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableKafka
@EnableFeignClients(basePackages = { "cl.teatromoro" })
@ComponentScan(basePackages = { "cl.teatromoro" })
public class TeatromoroUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatromoroUsuariosApplication.class, args);
	}

}
