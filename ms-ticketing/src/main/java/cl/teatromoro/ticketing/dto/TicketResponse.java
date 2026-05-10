package cl.teatromoro.ticketing.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketResponse {

    private Long id;
    private Integer idFuncion;
    private Integer idUsuario;
    private BigDecimal precioFinal;

}
