package cl.teatromoro.suscripciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableFeignClients
@ComponentScan(basePackages = { "cl.teatromoro" })
public class TeatromoroSuscripcionesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeatromoroSuscripcionesApplication.class, args);
    }
}