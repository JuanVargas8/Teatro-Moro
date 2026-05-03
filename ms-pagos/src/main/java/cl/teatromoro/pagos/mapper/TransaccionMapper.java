package cl.teatromoro.pagos.mapper;

import cl.teatromoro.pagos.dto.TransaccionRequest;
import cl.teatromoro.pagos.dto.TransaccionResponse;
import cl.teatromoro.pagos.model.Transaccion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransaccionMapper {
    TransaccionResponse toResponse(Transaccion transaccion);
    Transaccion toEntity(TransaccionRequest request);
}