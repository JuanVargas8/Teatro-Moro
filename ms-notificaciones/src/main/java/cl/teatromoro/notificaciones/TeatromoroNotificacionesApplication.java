package cl.teatromoro.notificaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TeatromoroNotificacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatromoroNotificacionesApplication.class, args);
	}

}
