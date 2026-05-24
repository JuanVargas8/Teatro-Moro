package cl.teatromoro.promociones.mapper;

import cl.teatromoro.promociones.dto.PromocionRequest;
import cl.teatromoro.promociones.dto.PromocionResponse;
import cl.teatromoro.promociones.model.Promocion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromocionMapper {

    Promocion requestToModel(PromocionRequest request);

    PromocionResponse modelToResponse(Promocion model);
}