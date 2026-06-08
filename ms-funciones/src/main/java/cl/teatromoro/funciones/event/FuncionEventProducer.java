package cl.teatromoro.funciones.event;

import cl.teatromoro.common.event.FuncionCreatedEvent;
import cl.teatromoro.common.event.FuncionDeletedEvent;
import cl.teatromoro.common.event.FuncionUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class FuncionEventProducer {

    private static final String TOPIC_BASE = "funciones.funcion";
    private static final String ID_NOT_NULL = "El ID no puede ser null";
    private static final String TOPIC_NOT_NULL = "El topic no puede ser null";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private void send(Object event, String eventType, String key) {
        String topic = Objects.requireNonNull(String.format("%s.%s", TOPIC_BASE, eventType), TOPIC_NOT_NULL);
        Objects.requireNonNull(key, ID_NOT_NULL);

        log.debug("Enviando evento Kafka → topic: {}, key: {}", topic, key);
        kafkaTemplate.send(topic, key, event);
    }

    public void sendCreated(FuncionCreatedEvent event) {
        send(event, "created", event.getId().toString());
    }

    public void sendUpdated(FuncionUpdatedEvent event) {
        send(event, "updated", event.getId().toString());
    }

    public void sendDeleted(FuncionDeletedEvent event) {
        send(event, "deleted", event.getId().toString());
    }
}