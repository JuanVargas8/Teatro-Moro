package cl.teatromoro.catalogo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ObraResponse extends RepresentationModel<ObraResponse> {

    private Long id;
    private String titulo;
    private String sinopsis;
    private Integer duracion;
    private String clasificacionEdad;
    private String categoriaNombre;
}
