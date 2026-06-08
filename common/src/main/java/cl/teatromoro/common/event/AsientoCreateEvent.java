package cl.teatromoro.common.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AsientoCreateEvent extends BaseEvent{

    private Long id;
    private Integer idFuncion;
    private String estado;

    private Long asientoId;
    private String asientoNumero; 
}

