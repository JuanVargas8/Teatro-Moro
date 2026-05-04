package cl.teatromoro.ticketing.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.ticketing.dto.TipoEntradaRequest;
import cl.teatromoro.ticketing.dto.TipoEntradaResponse;
import cl.teatromoro.ticketing.model.TipoEntrada;

@Component
public class TipoEntradaMapper {
        
    public TipoEntrada toEntity(TipoEntradaRequest request){
        return TipoEntrada.builder()
        .nombre(request.getNombre())
        .descuento(request.getDescuento())
        .build();
    }

    public TipoEntradaResponse toResponse(TipoEntrada entity){
        return TipoEntradaResponse.builder()
        .id(entity.getId())
        .nombre(entity.getNombre())
        .descuento(entity.getDescuento())
        .build();
    }

}
