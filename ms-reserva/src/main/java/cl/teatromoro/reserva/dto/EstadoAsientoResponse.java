package cl.teatromoro.reserva.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstadoAsientoResponse {
    private Long id;
    private Integer idFuncion;
    private String estado;

}
