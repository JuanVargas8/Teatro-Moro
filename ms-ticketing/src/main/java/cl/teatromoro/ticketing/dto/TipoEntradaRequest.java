package cl.teatromoro.ticketing.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TipoEntradaRequest {

    String nombre;
    BigDecimal descuento;

}
