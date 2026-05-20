package cl.teatromoro.catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TeatromoroCatalogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatromoroCatalogoApplication.class, args);
	}

}
