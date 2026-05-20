package cl.teatromoro.common.event;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TicketUpdateEvent extends BaseEvent {

    private Long id;
    private Integer idFuncion;
    private Integer idUsuario;
    private BigDecimal precioFinal;


    private Long tipoEntradaId;
    private String tipoEntradaNombre;
}