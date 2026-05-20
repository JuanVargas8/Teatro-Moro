package cl.teatromoro.pagos.dto;

import lombok.Data;

@Data
public class TransaccionResponse {
    private Long id;
    private Long idPedido;
    private Double monto;
    private String metodoPago;
    private String estado;
}