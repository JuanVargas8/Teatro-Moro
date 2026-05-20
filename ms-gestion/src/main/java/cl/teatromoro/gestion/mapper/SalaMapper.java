package cl.teatromoro.gestion.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.gestion.dto.SalaRequest;
import cl.teatromoro.gestion.dto.SalaResponse;
import cl.teatromoro.gestion.model.entity.Sala;

@Component
public class SalaMapper {

    public Sala toEntity(SalaRequest request) {
        return Sala.builder()
                .nombre(request.getNombre())
                .capacidadTotal(request.getCapacidadTotal())
                .descripcionTecnica(request.getDescripcionTecnica())
                .build();
    }

    public SalaResponse toResponse(Sala sala) {
        return SalaResponse.builder()
                .id(sala.getId())
                .nombre(sala.getNombre())
                .capacidadTotal(sala.getCapacidadTotal())
                .descripcionTecnica(sala.getDescripcionTecnica())
                .build();
    }
}
