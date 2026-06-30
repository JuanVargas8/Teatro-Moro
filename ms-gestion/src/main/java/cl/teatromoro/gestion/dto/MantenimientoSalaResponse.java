package cl.teatromoro.gestion.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class MantenimientoSalaResponse extends RepresentationModel<MantenimientoSalaResponse> {

    private Long id;

    private Long salaId;

    private String fechaInicio;
    private String fechaFin;

    private String descripcion;
}
