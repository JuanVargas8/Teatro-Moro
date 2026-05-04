package cl.teatromoro.notificaciones.mapper;


import org.springframework.stereotype.Component;

import cl.teatromoro.notificaciones.dto.PlantillaNotificacionRequest;
import cl.teatromoro.notificaciones.dto.PlantillaNotificacionResponse;
import cl.teatromoro.notificaciones.model.PlantillaNotificacion;

@Component
public class PlantillaNotificacionMapper {

    public PlantillaNotificacion toEntity(PlantillaNotificacionRequest request){
        return PlantillaNotificacion.builder()
        .tipo(request.getTipo())
        .cuerpo(request.getCuerpo())
        .build();
    }

    public PlantillaNotificacionResponse toResponse(PlantillaNotificacion entity){
        return PlantillaNotificacionResponse.builder()
        .id(entity.getId())
        .tipo(entity.getTipo())
        .cuerpo(entity.getCuerpo())
        .build();
    }
}
