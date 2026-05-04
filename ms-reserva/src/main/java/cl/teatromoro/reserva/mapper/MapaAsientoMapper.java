package cl.teatromoro.reserva.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.reserva.dto.MapaAsientoRequest;
import cl.teatromoro.reserva.dto.MapaAsientoResponse;
import cl.teatromoro.reserva.model.MapaAsiento;

@Component
public class MapaAsientoMapper {
    public MapaAsiento toEntity(MapaAsientoRequest request){
        return MapaAsiento.builder()
        .idSala(request.getIdSala())
        .fila(request.getFila())
        .numero(request.getNumero())
        .coordX(request.getCoordX())
        .build();
    }

    public MapaAsientoResponse toResponse(MapaAsiento entity){
        return MapaAsientoResponse.builder()
        .id(entity.getId())
        .idSala(entity.getIdSala())
        .fila(entity.getFila())
        .numero(entity.getNumero())
        .coordx(entity.getCoordX())
        .build();
    }
}
