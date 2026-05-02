package cl.teatromoro.gestion.dto;

import lombok.Data;

@Data
public class MantenimientoSalaRequest {

    private Long salaId;

    private String fechaInicio;
    private String fechaFin;

    private String descripcion;
}
