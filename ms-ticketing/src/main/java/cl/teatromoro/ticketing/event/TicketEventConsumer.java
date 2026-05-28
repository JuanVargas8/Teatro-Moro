package cl.teatromoro.ticketing.event;

import cl.teatromoro.common.event.TicketCreatedEvent;
import cl.teatromoro.common.event.TicketDeleteEvent;
import cl.teatromoro.common.event.TicketUpdateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TicketEventConsumer {

    @KafkaListener(topics = "ticketing.ticket.created", groupId = "ms-ticketing", properties = {
            "spring.json.value.default.type=cl.teatromoro.common.event.TicketCreatedEvent" })
    public void onTicketCreated(TicketCreatedEvent event) {
        log.debug("Evento recibido → created, id: {}", event.getId());
    }

    @KafkaListener(topics = "ticketing.ticket.updated", groupId = "ms-ticketing", properties = {
            "spring.json.value.default.type=cl.teatromoro.common.event.TicketUpdateEvent" })
    public void onTicketUpdated(TicketUpdateEvent event) {
        log.debug("Evento recibido → updated, id: {}", event.getId());
    }

    @KafkaListener(topics = "ticketing.ticket.deleted", groupId = "ms-ticketing", properties = {
            "spring.json.value.default.type=cl.teatromoro.common.event.TicketDeleteEvent" })
    public void onTicketDeleted(TicketDeleteEvent event) {
        log.debug("Evento recibido → deleted, id: {}", event.getId());
    }
}