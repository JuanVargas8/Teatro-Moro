package cl.teatromoro.gestion.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ZonaSalaResponse {

    private Long id;

    private Long salaId;
    private String nombre;
    private Double multiplicadorPrecio;
}
