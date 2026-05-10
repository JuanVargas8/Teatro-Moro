package cl.teatromoro.reserva.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MapaAsientoResponse {
    private Long id;
    private Integer idSala;
    private String fila;
    private Integer numero;
    private Integer coordx;
}
