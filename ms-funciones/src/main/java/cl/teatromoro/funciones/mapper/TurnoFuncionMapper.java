package cl.teatromoro.funciones.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.funciones.dto.TurnoFuncionRequest;
import cl.teatromoro.funciones.dto.TurnoFuncionResponse;
import cl.teatromoro.funciones.model.entity.Funcion;
import cl.teatromoro.funciones.model.entity.TurnoFuncion;

@Component
public class TurnoFuncionMapper {

    public TurnoFuncion toEntity(TurnoFuncionRequest request) {

        return TurnoFuncion.builder()
                .idPersonalCargo(request.getPersonalCargoId())
                .funcion(Funcion.builder()
                .id(request.getFuncionId())
                .build())
                .build();
    }


    public TurnoFuncionResponse toResponse(TurnoFuncion t) {
        return TurnoFuncionResponse.builder()
                .id(t.getId())
                .personalCargoId(t.getIdPersonalCargo())
                .funcionId(
                t.getFuncion() != null
                ? t.getFuncion().getId()
                : null)
                .build();
    }
}
