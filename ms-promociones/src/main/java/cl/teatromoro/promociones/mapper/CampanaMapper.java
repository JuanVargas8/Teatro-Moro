package cl.teatromoro.promociones.mapper;

import cl.teatromoro.promociones.dto.CampanaRequest;
import cl.teatromoro.promociones.dto.CampanaResponse;
import cl.teatromoro.promociones.model.Campana;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CampanaMapper {

    Campana requestToModel(CampanaRequest request);

    CampanaResponse modelToResponse(Campana model);
}