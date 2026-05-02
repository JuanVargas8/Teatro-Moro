package cl.teatromoro.funciones.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TurnoFuncionResponse {

    private Long id;
    private Long funcionId;
    private Long personalCargoId;
}
