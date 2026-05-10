package cl.teatromoro.informes.dto;

import lombok.Data;

@Data
public class MetricasOcupacionRequest {
    private Long idFuncion;
    private Double porcentajeLlenado;
}
