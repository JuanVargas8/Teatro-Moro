package cl.teatromoro.reserva.dto;

import lombok.Data;

@Data
public class MapaAsientoRequest {
    private Integer idSala;
    private String fila;
    private Integer numero;
    private Integer coordX;
}
