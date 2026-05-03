package cl.teatromoro.informes.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetricasOcupacionResponse {
    private Long id;
    private Long idFuncion;
    private Double porcentajeLlenado;
}
