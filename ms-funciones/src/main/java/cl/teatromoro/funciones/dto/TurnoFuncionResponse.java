package cl.teatromoro.funciones.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TurnoFuncionResponse extends RepresentationModel<TurnoFuncionResponse> {

    private Long id;
    private Long funcionId;
    private Long personalCargoId;
}
