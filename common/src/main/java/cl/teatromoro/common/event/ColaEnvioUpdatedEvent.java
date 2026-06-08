package cl.teatromoro.common.event;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ColaEnvioUpdatedEvent extends BaseEvent {

    private Long id;
    private Integer idUsuario;
    private String estado;
    private Integer reintentos;

    private Long plantillaId;
    private String plantillaTipo;
}