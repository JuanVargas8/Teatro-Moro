package cl.teatromoro.promociones.mapper;

import cl.teatromoro.promociones.model.Campana;
import cl.teatromoro.promociones.dto.CampanaRequest;
import cl.teatromoro.promociones.dto.CampanaResponse;
import cl.teatromoro.common.event.CampanaEvent;
import org.springframework.stereotype.Component;

@Component
public class CampanaMapper {

    // 1. De lo que llega de Postman (Request) a la Base de Datos (Entity)
    public Campana toEntity(CampanaRequest request) {
        Campana entity = new Campana();
        entity.setNombre(request.getNombre());
        entity.setFechaInicio(request.getFechaInicio());
        entity.setFechaFin(request.getFechaFin());
        return entity;
    }

    // 2. De la Base de Datos (Entity) a lo que ve el usuario (Response)
    public CampanaResponse toResponse(Campana entity) {
        CampanaResponse response = new CampanaResponse();
        response.setId(entity.getId());
        response.setNombre(entity.getNombre());
        response.setFechaInicio(entity.getFechaInicio());
        response.setFechaFin(entity.getFechaFin());
        return response;
    }

    // 3. De la Base de Datos (Entity) al Evento de Kafka
    public CampanaEvent toEvent(Campana entity) {
        return new CampanaEvent(
            entity.getId(),
            entity.getNombre(),
            entity.getFechaInicio(),
            entity.getFechaFin()
        );
    }
}