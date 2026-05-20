package cl.teatromoro.gestion.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.gestion.dto.ZonaSalaRequest;
import cl.teatromoro.gestion.dto.ZonaSalaResponse;
import cl.teatromoro.gestion.model.entity.Sala;
import cl.teatromoro.gestion.model.entity.ZonaSala;

@Component
public class ZonaSalaMapper {

    public ZonaSala toEntity(ZonaSalaRequest request, Sala sala) {
        return ZonaSala.builder()
                .nombre(request.getNombre())
                .multiplicadorPrecio(request.getMultiplicadorPrecio())
                .sala(sala)
                .build();
    }

    public ZonaSalaResponse toResponse(ZonaSala zona) {
        return ZonaSalaResponse.builder()
                .id(zona.getId())
                .nombre(zona.getNombre())
                .multiplicadorPrecio(zona.getMultiplicadorPrecio())
                .salaId(zona.getSala().getId())
                .build();
    }
}
