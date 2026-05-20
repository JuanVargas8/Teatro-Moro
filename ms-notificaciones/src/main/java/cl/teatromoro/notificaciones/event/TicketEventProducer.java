package cl.teatromoro.notificaciones.event;


import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TicketEventProducer {
    private static final String TOPIC_BASE = "";
    private static final String ID_NOT_NULL = "El ID no puede ser null";
    private static final String TOPIC_NOT_NULL = "El topic no puede ser null";

}
