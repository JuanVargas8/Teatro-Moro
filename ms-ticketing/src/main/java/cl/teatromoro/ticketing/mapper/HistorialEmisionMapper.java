package cl.teatromoro.ticketing.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.ticketing.dto.HistorialEmisionRequest;
import cl.teatromoro.ticketing.dto.HistorialEmisionResponse;
import cl.teatromoro.ticketing.model.HistorialEmision;


@Component
public class HistorialEmisionMapper{

    public HistorialEmision toEntity(HistorialEmisionRequest request){

    return HistorialEmision.builder()
        .fechaEmision(request.getFechaEmision())
        .canalVenta(request.getCanalVenta())
        .build();
    }


    public HistorialEmisionResponse toResponse(HistorialEmision entity) {
        return HistorialEmisionResponse.builder()
                .id(entity.getId())
                .fechaEmision(entity.getFechaEmision())
                .canalVenta(entity.getCanalVenta())
                .build();
    }
}




