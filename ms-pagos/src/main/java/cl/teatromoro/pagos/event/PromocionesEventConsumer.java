package cl.teatromoro.pagos.event;

import cl.teatromoro.common.event.CampanaDeletedEvent;
import cl.teatromoro.common.event.CampanaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PromocionesEventConsumer {

    @KafkaListener(
        topics = "promociones.campana.created",
        groupId = "ms-pagos",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.CampanaEvent"}
    )
    public void onCampanaCreada(CampanaEvent event) {
        log.debug("Evento recibido en ms-pagos → campana.created, id: {} nombre: {}", event.getId(), event.getNombre());
        // TODO: Integrar campaña recibida con la lógica de pagos, descuentos o promociones activas.
    }

    @KafkaListener(
        topics = "promociones.campana.deleted",
        groupId = "ms-pagos",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.CampanaDeletedEvent"}
    )
    public void onCampanaEliminada(CampanaDeletedEvent event) {
        log.debug("Evento recibido en ms-pagos → campana.deleted, id: {}", event.getId());
        // TODO: Actualizar reglas de pagos / promociones eliminadas si es necesario.
    }
}
