package cl.teatromoro.notificaciones.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.notificaciones.dto.ColaEnvioRequest;
import cl.teatromoro.notificaciones.dto.ColaEnvioResponse;
import cl.teatromoro.notificaciones.model.ColaEnvio;

@Component
public class ColaEnvioMapper {
    public ColaEnvio toEntity(ColaEnvioRequest request){
        return ColaEnvio.builder()
        .idUsuario(request.getIdUsuario())
        .estado(request.getEstado())
        .reintentos(request.getReintentos())
        .build();
    }

    public ColaEnvioResponse toResponse(ColaEnvio entity){
        return ColaEnvioResponse.builder()
        .id(entity.getId())
        .idUsuario(entity.getIdUsuario())
        .estado(entity.getEstado())
        .reintentos(entity.getReintentos())
        .build();
    }
}
