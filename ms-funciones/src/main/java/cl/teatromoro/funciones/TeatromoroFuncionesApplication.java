package cl.teatromoro.funciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = { "cl.teatromoro" })
public class TeatromoroFuncionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatromoroFuncionesApplication.class, args);
	}

}
