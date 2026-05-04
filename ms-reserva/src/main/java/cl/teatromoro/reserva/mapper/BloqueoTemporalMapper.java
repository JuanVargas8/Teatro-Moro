package cl.teatromoro.reserva.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.reserva.dto.BloqueoTemporalRequest;
import cl.teatromoro.reserva.dto.BloqueoTemporalResponse;
import cl.teatromoro.reserva.model.BloqueoTemporal;

@Component
public class BloqueoTemporalMapper {

    public BloqueoTemporal toEntity(BloqueoTemporalRequest request) {
        return BloqueoTemporal.builder()
                .idSesionUsuario(request.getIdSesionUsuario())
                .expiracion(request.getExpiracion())
                .build();

    }

    public BloqueoTemporalResponse toResponse(BloqueoTemporal entity) {
        return BloqueoTemporalResponse.builder()
                .id(entity.getId())
                .idSesionUsuario(entity.getIdSesionUsuario())
                .expiracion(entity.getExpiracion())
                .build();
    }

}
