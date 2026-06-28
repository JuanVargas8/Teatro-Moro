package cl.teatromoro.common.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AbonadoDeletedEvent extends BaseEvent {
    private Long id;
    private Long usuarioId;
    private Long planId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
