package cl.teatromoro.pagos.dto;

import lombok.Data;

@Data
public class TransaccionRequest {
    private Long idPedido;
    private Double monto;
    private String metodoPago;
}