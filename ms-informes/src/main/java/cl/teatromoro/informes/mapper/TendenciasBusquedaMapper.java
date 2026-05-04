package cl.teatromoro.informes.mapper;

import org.springframework.stereotype.Component;
import cl.teatromoro.informes.dto.TendenciasBusquedaRequest;
import cl.teatromoro.informes.dto.TendenciasBusquedaResponse;
import cl.teatromoro.informes.model.entity.TendenciasBusqueda;

@Component
public class TendenciasBusquedaMapper {

    public TendenciasBusqueda toEntity(TendenciasBusquedaRequest request) {
        return TendenciasBusqueda.builder()
                .terminoBusqueda(request.getTerminoBusqueda())
                .cantidadResultados(request.getCantidadResultados())
                .fecha(request.getFecha())
                .build();
    }

    public TendenciasBusquedaResponse toResponse(TendenciasBusqueda entity) {
        return TendenciasBusquedaResponse.builder()
                .id(entity.getId())
                .terminoBusqueda(entity.getTerminoBusqueda())
                .cantidadResultados(entity.getCantidadResultados())
                .fecha(entity.getFecha())
                .build();
    }
}
