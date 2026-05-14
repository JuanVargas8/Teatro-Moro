package cl.teatromoro.catalogo.event;

import cl.teatromoro.common.event.PeliculaCreatedEvent;
import cl.teatromoro.common.event.PeliculaDeletedEvent;
import cl.teatromoro.common.event.PeliculaUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PeliculaEventConsumer {

    @KafkaListener(
        topics = "catalogo.pelicula.created",
        groupId = "ms-catalogo",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.PeliculaCreatedEvent"}
    )
    public void onPeliculaCreated(PeliculaCreatedEvent event) {
        log.debug("Evento recibido → created, id: {}", event.getId());
        // TODO: Implementar lógica para manejar creación de película
    }

    @KafkaListener(
        topics = "catalogo.pelicula.updated",
        groupId = "ms-catalogo",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.PeliculaUpdatedEvent"}
    )
    public void onPeliculaUpdated(PeliculaUpdatedEvent event) {
        log.debug("Evento recibido → updated, id: {}", event.getId());
        // TODO: Implementar lógica para manejar actualización de película
    }

    @KafkaListener(
        topics = "catalogo.pelicula.deleted",
        groupId = "ms-catalogo",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.PeliculaDeletedEvent"}
    )
    public void onPeliculaDeleted(PeliculaDeletedEvent event) {
        log.debug("Evento recibido → deleted, id: {}", event.getId());
        // TODO: Implementar lógica para manejar eliminación de película
    }
}