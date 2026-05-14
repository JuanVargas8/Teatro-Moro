package cl.teatromoro.gestion.event;

import cl.teatromoro.common.event.UsuarioCreatedEvent;
import cl.teatromoro.common.event.UsuarioDeletedEvent;
import cl.teatromoro.common.event.UsuarioUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UsuarioEventConsumer {

    @KafkaListener(
        topics = "gestion.usuario.created",
        groupId = "ms-gestion",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.UsuarioCreatedEvent"}
    )
    public void onUsuarioCreated(UsuarioCreatedEvent event) {
        log.debug("Evento recibido → created, id: {}", event.getId());
        // TODO: Implementar lógica para manejar creación de usuario
    }

    @KafkaListener(
        topics = "gestion.usuario.updated",
        groupId = "ms-gestion",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.UsuarioUpdatedEvent"}
    )
    public void onUsuarioUpdated(UsuarioUpdatedEvent event) {
        log.debug("Evento recibido → updated, id: {}", event.getId());
        // TODO: Implementar lógica para manejar actualización de usuario
    }

    @KafkaListener(
        topics = "gestion.usuario.deleted",
        groupId = "ms-gestion",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.UsuarioDeletedEvent"}
    )
    public void onUsuarioDeleted(UsuarioDeletedEvent event) {
        log.debug("Evento recibido → deleted, id: {}", event.getId());
        // TODO: Implementar lógica para manejar eliminación de usuario
    }
}