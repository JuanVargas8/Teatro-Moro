package cl.teatromoro.gestion.dto;

import lombok.Data;

@Data
public class ZonaSalaRequest {

    private Long salaId;
    private String nombre;
    private Double multiplicadorPrecio;
}
