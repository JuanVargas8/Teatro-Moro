package cl.teatromoro.informes.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TendenciasBusquedaRequest {
    private String terminoBusqueda;
    private Integer cantidadResultados;
    private LocalDate fecha;
}
