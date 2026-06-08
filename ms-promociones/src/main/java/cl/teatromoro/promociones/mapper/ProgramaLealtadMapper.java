package cl.teatromoro.promociones.mapper;

import cl.teatromoro.promociones.dto.ProgramaLealtadRequest;
import cl.teatromoro.promociones.dto.ProgramaLealtadResponse;
import cl.teatromoro.promociones.model.ProgramaLealtad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProgramaLealtadMapper {

    ProgramaLealtad requestToModel(ProgramaLealtadRequest request);

    ProgramaLealtadResponse modelToResponse(ProgramaLealtad model);
}