package cl.teatromoro.catalogo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class MultimediaObraResponse extends RepresentationModel<MultimediaObraResponse> {

    private Long id;
    private String urlImagen;
    private String tipo;
    private Long obraId;
}
