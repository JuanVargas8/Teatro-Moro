package cl.teatromoro.gestion.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ZonaSalaResponse extends RepresentationModel<ZonaSalaResponse> {

    private Long id;

    private Long salaId;
    private String nombre;
    private Double multiplicadorPrecio;
}
