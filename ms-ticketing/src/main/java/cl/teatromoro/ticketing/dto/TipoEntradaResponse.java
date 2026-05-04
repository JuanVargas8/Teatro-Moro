package cl.teatromoro.ticketing.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TipoEntradaResponse {
    private Long id;
    private String nombre;
    private BigDecimal descuento;
}
