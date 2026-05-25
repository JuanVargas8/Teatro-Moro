package cl.teatromoro.funciones.event;

import cl.teatromoro.common.event.FuncionCreatedEvent;
import cl.teatromoro.common.event.FuncionDeletedEvent;
import cl.teatromoro.common.event.FuncionUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FuncionEventConsumer {

    @KafkaListener(
        topics = "funciones.funcion.created",
        groupId = "ms-funciones",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.FuncionCreatedEvent"}
    )
    public void onFuncionCreated(FuncionCreatedEvent event) {
        log.info("Evento recibido → created, id: {}", event.getId());
        // TODO: Implementar lógica para manejar creación de función
    }

    @KafkaListener(
        topics = "funciones.funcion.updated",
        groupId = "ms-funciones",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.FuncionUpdatedEvent"}
    )
    public void onFuncionUpdated(FuncionUpdatedEvent event) {
        log.info("Evento recibido → updated, id: {}", event.getId());
        // TODO: Implementar lógica para manejar actualización de función
    }

    @KafkaListener(
        topics = "funciones.funcion.deleted",
        groupId = "ms-funciones",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.FuncionDeletedEvent"}
    )
    public void onFuncionDeleted(FuncionDeletedEvent event) {
        log.info("Evento recibido → deleted, id: {}", event.getId());
        // TODO: Implementar lógica para manejar eliminación de función
    }
}