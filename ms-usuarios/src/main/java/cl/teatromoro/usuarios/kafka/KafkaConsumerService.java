package cl.teatromoro.usuarios.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(
            topics = "usuarios-topic",
            groupId = "ms-usuarios"
    )
    public void consumir(String mensaje) {

        String[] datos = mensaje.split(":");

        if (datos.length >= 2) {

            System.out.println(
                    "Usuario creado -> ID: "
                            + datos[0]
                            + ", Nombre: "
                            + datos[1]
            );

        } else {

            System.out.println(
                    "Mensaje recibido: " + mensaje
            );
        }
    }
}