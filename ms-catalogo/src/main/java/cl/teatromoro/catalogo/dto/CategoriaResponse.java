package cl.teatromoro.catalogo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CategoriaResponse extends RepresentationModel<CategoriaResponse> {

    private Long id;
    private String nombre;
    private String descripcion;
}
