package cl.teatromoro.pagos.mapper;

import cl.teatromoro.pagos.dto.ReembolsoRequest;
import cl.teatromoro.pagos.dto.ReembolsoResponse;
import cl.teatromoro.pagos.model.Reembolso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReembolsoMapper {

    Reembolso requestToModel(ReembolsoRequest request);

    ReembolsoResponse modelToResponse(Reembolso model);
}