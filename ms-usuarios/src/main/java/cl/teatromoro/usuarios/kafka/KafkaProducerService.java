package cl.teatromoro.usuarios.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(
            KafkaTemplate<String, String> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarMensaje(String mensaje) {

        kafkaTemplate.send(
                "usuarios-topic",
                mensaje
        );
    }
}