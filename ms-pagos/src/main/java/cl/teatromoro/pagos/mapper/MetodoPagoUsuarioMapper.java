package cl.teatromoro.pagos.mapper;

import cl.teatromoro.pagos.dto.MetodoPagoUsuarioRequest;
import cl.teatromoro.pagos.dto.MetodoPagoUsuarioResponse;
import cl.teatromoro.pagos.model.MetodoPagoUsuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MetodoPagoUsuarioMapper {
    
    MetodoPagoUsuario requestToModel(MetodoPagoUsuarioRequest request);
    
    MetodoPagoUsuarioResponse modelToResponse(MetodoPagoUsuario model);
}