package cl.teatromoro.personal.mapper;

import org.springframework.stereotype.Component;
import cl.teatromoro.personal.dto.AsistenciaRequest;
import cl.teatromoro.personal.dto.AsistenciaResponse;
import cl.teatromoro.personal.model.entity.Asistencia;

@Component
public class AsistenciaMapper {

    public Asistencia toEntity(AsistenciaRequest request) {
        return Asistencia.builder()
                .idPersonal(request.getIdPersonal())
                .idFuncion(request.getIdFuncion())
                .horaEntrada(request.getHoraEntrada())
                .build();
    }

    public AsistenciaResponse toResponse(Asistencia entity) {
        return AsistenciaResponse.builder()
                .id(entity.getId())
                .idPersonal(entity.getIdPersonal())
                .idFuncion(entity.getIdFuncion())
                .horaEntrada(entity.getHoraEntrada())
                .build();
    }
}
