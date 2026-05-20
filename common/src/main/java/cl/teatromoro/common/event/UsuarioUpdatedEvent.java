package cl.teatromoro.common.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UsuarioUpdatedEvent extends BaseEvent {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
}