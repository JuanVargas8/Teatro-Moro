package cl.teatromoro.catalogo.dto;

import lombok.Data;

@Data
public class MultimediaObraRequest {

    private String urlImagen;
    private String tipo;
    private Long obraId;
}
