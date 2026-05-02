package cl.teatromoro.catalogo.dto;

import lombok.Data;

@Data
public class ObraRequest {

    private String titulo;
    private String sinopsis;
    private Integer duracion;
    private String clasificacionEdad;
    private Long categoriaId;
}
