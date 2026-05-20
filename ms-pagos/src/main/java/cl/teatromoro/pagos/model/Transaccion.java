package cl.teatromoro.pagos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transacciones")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idPedido;
    private Double monto;
    private String metodoPago;
    private String estado;
}