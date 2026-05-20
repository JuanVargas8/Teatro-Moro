package cl.teatromoro.suscripciones.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(
            topics = "abonados-topic",
            groupId = "ms-suscripciones"
    )
    public void consumir(String mensaje) {

        System.out.println(
                "Mensaje recibido Kafka: "
                + mensaje
        );
    }
}