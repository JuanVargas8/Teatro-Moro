package cl.teatromoro.promociones.kafka;

import cl.teatromoro.common.event.CampanaDeletedEvent;
import cl.teatromoro.common.event.CampanaEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class PromocionesEventProducer {

    private static final String TOPIC_BASE = "promociones.campana";
    private static final String TOPIC_NOT_NULL = "El topic no puede ser null";
    private static final String KEY_NOT_NULL = "La clave del evento no puede ser null";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private void send(Object event, String eventType, String key) {
        String topic = Objects.requireNonNull(String.format("%s.%s", TOPIC_BASE, eventType), TOPIC_NOT_NULL);
        Objects.requireNonNull(key, KEY_NOT_NULL);

        log.debug("Enviando evento Kafka → topic: {}, key: {}", topic, key);
        kafkaTemplate.send(topic, key, event);
    }

    public void enviarCampanaCreada(CampanaEvent evento) {
        send(evento, "created", evento.getId().toString());
    }

    public void enviarCampanaEliminada(Long id) {
        CampanaDeletedEvent evento = new CampanaDeletedEvent(id);
        send(evento, "deleted", id.toString());
    }
}