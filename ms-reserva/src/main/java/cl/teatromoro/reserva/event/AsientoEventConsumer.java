package cl.teatromoro.reserva.event;

import cl.teatromoro.common.event.AsientoCreateEvent;
import cl.teatromoro.common.event.AsientoDeleteEvent;
import cl.teatromoro.common.event.AsientoUpdateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsientoEventConsumer {

    @KafkaListener(
        topics = "reserva.asiento.created",
        groupId = "ms-reserva",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.AsientoCreateEvent"}
    )
    public void onAsientoCreated(AsientoCreateEvent event) {
        log.debug("Evento recibido → created, id: {}", event.getId());
    }

    @KafkaListener(
        topics = "reserva.asiento.updated",
        groupId = "ms-reserva",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.AsientoUpdateEvent"}
    )
    public void onAsientoUpdated(AsientoUpdateEvent event) {
        log.debug("Evento recibido → updated, id: {}", event.getId());
    }

    @KafkaListener(
        topics = "reserva.asiento.deleted",
        groupId = "ms-reserva",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.AsientoDeleteEvent"}
    )
    public void onAsientoDeleted(AsientoDeleteEvent event) {
        log.debug("Evento recibido → deleted, id: {}", event.getId());
    }
}