package cl.teatromoro.funciones.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.funciones.dto.ExcepcionHorarioRequest;
import cl.teatromoro.funciones.dto.ExcepcionHorarioResponse;
import cl.teatromoro.funciones.model.entity.ExcepcionHorario;

@Component
public class ExcepcionHorarioMapper {

    public ExcepcionHorario toEntity(ExcepcionHorarioRequest request) {
        ExcepcionHorario e = new ExcepcionHorario();
        e.setFecha(request.getFecha());
        e.setMotivo(request.getMotivo());
        return e;
    }

    public ExcepcionHorarioResponse toResponse(ExcepcionHorario e) {
        return ExcepcionHorarioResponse.builder()
                .id(e.getId())
                .fecha(e.getFecha())
                .motivo(e.getMotivo())
                .build();
    }
}
