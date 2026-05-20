package cl.teatromoro.promociones.event;

import cl.teatromoro.common.event.TransaccionCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PagosEventConsumer {

    @KafkaListener(
        topics = "pagos.transaccion.created",
        groupId = "ms-promociones",
        properties = {"spring.json.value.default.type=cl.teatromoro.common.event.TransaccionCreatedEvent"}
    )
    public void onTransaccionCreada(TransaccionCreatedEvent event) {
        log.debug("Evento recibido en ms-promociones → pagos.transaccion.created, id: {} monto: {} metodo: {}", event.getId(), event.getMonto(), event.getMetodoPago());
        // TODO: Integrar datos de pago para ajustar campañas y promociones en ms-promociones.
    }
}
