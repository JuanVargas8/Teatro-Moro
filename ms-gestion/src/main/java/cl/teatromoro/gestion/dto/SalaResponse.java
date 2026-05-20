package cl.teatromoro.gestion.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalaResponse {

    private Long id;

    private String nombre;
    private Integer capacidadTotal;
    private String descripcionTecnica;
}
