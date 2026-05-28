package cl.teatromoro.funciones.event;

import cl.teatromoro.common.event.FuncionCreatedEvent;
import cl.teatromoro.common.event.FuncionDeletedEvent;
import cl.teatromoro.common.event.FuncionUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

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

        log.info("Preparando envío a Kafka → topic: {}, key: {}", topic, key);

        Message<Object> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.KEY, key)
                .build();

        kafkaTemplate.send(message).whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Evento ENVIADO con éxito a Kafka → topic: {}, partition: {}, offset: {}",
                        topic,
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            } else {
                log.error("Error al enviar evento a Kafka → topic: {}", topic, ex);
            }
        });
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