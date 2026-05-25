package cl.teatromoro.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionCreatedEvent {
    private Long id;
    private Long idPedido;
    private Double monto;
    private String metodoPago;
    private String estado;
}
