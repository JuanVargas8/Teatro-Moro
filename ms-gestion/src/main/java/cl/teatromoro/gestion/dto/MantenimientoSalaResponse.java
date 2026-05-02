package cl.teatromoro.gestion.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MantenimientoSalaResponse {

    private Long id;

    private Long salaId;

    private String fechaInicio;
    private String fechaFin;

    private String descripcion;
}
