package cl.teatromoro.funciones.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ExcepcionHorarioResponse extends RepresentationModel<ExcepcionHorarioResponse> {

    private Long id;
    private LocalDate fecha;
    private String motivo;
}
