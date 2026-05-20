package cl.teatromoro.catalogo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MultimediaObraResponse {

    private Long id;
    private String urlImagen;
    private String tipo;
    private Long obraId;
}
