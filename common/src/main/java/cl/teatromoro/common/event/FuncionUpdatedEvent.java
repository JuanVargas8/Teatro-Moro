package cl.teatromoro.common.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FuncionUpdatedEvent extends BaseEvent {
    private Long id;
    private Long peliculaId;
    private Long salaId;
    private LocalDateTime fechaHora;
    private Double precio;
}