package cl.teatromoro.notificaciones.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import cl.teatromoro.common.event.ColaEnvioCreatedEvent;
import cl.teatromoro.common.event.ColaEnvioDeletedEvent;
import cl.teatromoro.common.event.ColaEnvioUpdatedEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ColaEnvioEventConsumer {

    @KafkaListener(
        topics = "notificaciones.notificacion.created",
        groupId = "ms-notificaciones"
    )
    public void onCreated(Object event) {
        log.debug("Evento recibido → created {}", event);
    }

    @KafkaListener(
        topics = "notificaciones.notificacion.updated",
        groupId = "ms-notificaciones"
    )
    public void onUpdated(Object event) {
        log.debug("Evento recibido → updated {}", event);
    }

    @KafkaListener(
        topics = "notificaciones.notificacion.deleted",
        groupId = "ms-notificaciones"
    )
    public void onDeleted(Object event) {
        log.debug("Evento recibido → deleted {}", event);
    }
}