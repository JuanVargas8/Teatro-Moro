package cl.teatromoro.informes.mapper;

import org.springframework.stereotype.Component;
import cl.teatromoro.informes.dto.MetricasOcupacionRequest;
import cl.teatromoro.informes.dto.MetricasOcupacionResponse;
import cl.teatromoro.informes.model.entity.MetricasOcupacion;

@Component
public class MetricasOcupacionMapper {

    public MetricasOcupacion toEntity(MetricasOcupacionRequest request) {
        return MetricasOcupacion.builder()
                .idFuncion(request.getIdFuncion())
                .porcentajeLlenado(request.getPorcentajeLlenado())
                .build();
    }

    public MetricasOcupacionResponse toResponse(MetricasOcupacion entity) {
        return MetricasOcupacionResponse.builder()
                .id(entity.getId())
                .idFuncion(entity.getIdFuncion())
                .porcentajeLlenado(entity.getPorcentajeLlenado())
                .build();
    }
}
