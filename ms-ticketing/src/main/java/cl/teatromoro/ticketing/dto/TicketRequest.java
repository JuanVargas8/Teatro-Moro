package cl.teatromoro.ticketing.dto;

import java.math.BigDecimal;

import lombok.Data;


@Data
public class TicketRequest {

    private Integer idFuncion;
    private Integer idUsuario;
    private BigDecimal precioFinal;

    private Long idTipoEntrada;

}
