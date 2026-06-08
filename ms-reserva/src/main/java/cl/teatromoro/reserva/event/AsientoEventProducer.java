package cl.teatromoro.reserva.event;

import cl.teatromoro.common.event.AsientoCreateEvent;
import cl.teatromoro.common.event.AsientoDeleteEvent;
import cl.teatromoro.common.event.AsientoUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AsientoEventProducer {

    private static final String TOPIC_BASE = "reserva.asiento";
    private static final String ID_NOT_NULL = "El ID no puede ser null";
    private static final String TOPIC_NOT_NULL = "El topic no puede ser null";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private void send(Object event, String eventType, String key) {
        String topic = Objects.requireNonNull(String.format("%s.%s", TOPIC_BASE, eventType), TOPIC_NOT_NULL);
        Objects.requireNonNull(key, ID_NOT_NULL);

        log.debug("Enviando evento Kafka → topic: {}, key: {}", topic, key);
        kafkaTemplate.send(topic, key, event);
    }

    public void sendCreated(AsientoCreateEvent event) {
        send(event, "created", event.getId().toString());
    }

    public void sendUpdated(AsientoUpdateEvent event) {
        send(event, "updated", event.getId().toString());
    }

    public void sendDeleted(AsientoDeleteEvent event) {
        send(event, "deleted", event.getId().toString());
    }
}