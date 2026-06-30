package cl.teatromoro.catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = { "cl.teatromoro" })
@ComponentScan(basePackages = { "cl.teatromoro" })
public class TeatromoroCatalogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatromoroCatalogoApplication.class, args);
	}

}
