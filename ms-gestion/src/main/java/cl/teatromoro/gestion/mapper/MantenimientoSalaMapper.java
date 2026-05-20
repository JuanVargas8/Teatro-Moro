package cl.teatromoro.gestion.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import cl.teatromoro.gestion.dto.MantenimientoSalaRequest;
import cl.teatromoro.gestion.dto.MantenimientoSalaResponse;
import cl.teatromoro.gestion.model.entity.MantenimientoSala;
import cl.teatromoro.gestion.model.entity.Sala;

@Component
public class MantenimientoSalaMapper {

    public MantenimientoSala toEntity(MantenimientoSalaRequest request, Sala sala) {
        return MantenimientoSala.builder()
                .fechaInicio(LocalDate.parse(request.getFechaInicio()))
                .fechaFin(LocalDate.parse(request.getFechaFin()))
                .descripcion(request.getDescripcion())
                .sala(sala)
                .build();
    }

    public MantenimientoSalaResponse toResponse(MantenimientoSala m) {
        return MantenimientoSalaResponse.builder()
                .id(m.getId())
                .fechaInicio(m.getFechaInicio().toString())
                .fechaFin(m.getFechaFin().toString())
                .descripcion(m.getDescripcion())
                .salaId(m.getSala().getId())
                .build();
    }
}
