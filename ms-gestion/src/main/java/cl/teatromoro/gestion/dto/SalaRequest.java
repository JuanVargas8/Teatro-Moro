package cl.teatromoro.gestion.dto;

import lombok.Data;

@Data
public class SalaRequest {

    private String nombre;
    private Integer capacidadTotal;
    private String descripcionTecnica;
}
