package cl.teatromoro.informes.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TendenciasBusquedaResponse {
    private Long id;
    private String terminoBusqueda;
    private Integer cantidadResultados;
    private LocalDate fecha;
}
