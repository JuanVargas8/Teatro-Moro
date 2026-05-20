package cl.teatromoro.common.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PeliculaUpdatedEvent extends BaseEvent {
    private Long id;
    private String titulo;
    private String descripcion;
    private Integer duracion;
    private String genero;
}