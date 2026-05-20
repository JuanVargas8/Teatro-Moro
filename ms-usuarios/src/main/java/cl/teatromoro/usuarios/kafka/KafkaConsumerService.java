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

        System.out.println(
                "Mensaje Kafka recibido: "
                + mensaje
        );
    }
}