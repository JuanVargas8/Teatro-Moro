package cl.teatromoro.gestion.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class SalaResponse extends RepresentationModel<SalaResponse> {

    private Long id;

    private String nombre;
    private Integer capacidadTotal;
    private String descripcionTecnica;
}
