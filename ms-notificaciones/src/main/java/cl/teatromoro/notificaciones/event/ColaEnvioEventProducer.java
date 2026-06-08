package cl.teatromoro.notificaciones.event;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ColaEnvioEventProducer {

    private static final String TOPIC_BASE = "notificaciones.notificacion";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private void send(Object event, String eventType, String key) {
        String topic = String.format("%s.%s", TOPIC_BASE, eventType);

        log.debug("Enviando evento Kafka → topic: {}, key: {}", topic, key);
        kafkaTemplate.send(topic, key, event);
    }

    public void sendCreated(Object event) {
        send(event, "created", "key");
    }

    public void sendUpdated(Object event) {
        send(event, "updated", "key");
    }

    public void sendDeleted(Object event) {
        send(event, "deleted", "key");
    }
}