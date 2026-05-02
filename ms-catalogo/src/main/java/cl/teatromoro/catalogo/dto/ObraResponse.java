package cl.teatromoro.catalogo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ObraResponse {

    private Long id;
    private String titulo;
    private String sinopsis;
    private Integer duracion;
    private String clasificacionEdad;
    private String categoriaNombre;
}
