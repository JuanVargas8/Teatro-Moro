package cl.teatromoro.reserva.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.reserva.dto.EstadoAsientoRequest;
import cl.teatromoro.reserva.dto.EstadoAsientoResponse;
import cl.teatromoro.reserva.model.EstadoAsiento;

@Component
public class EstadoAsientoMapper {
    public EstadoAsiento toEntity(EstadoAsientoRequest request){
        return EstadoAsiento.builder()
        .idFuncion(request.getIdFuncion())
        .estado(request.getEstado())
        .build();
    }

    public EstadoAsientoResponse toResponse(EstadoAsiento entity){
        return EstadoAsientoResponse.builder()
        .id(entity.getId())
        .idFuncion(entity.getIdFuncion())
        .estado(entity.getEstado())
        .build();
    }
}
