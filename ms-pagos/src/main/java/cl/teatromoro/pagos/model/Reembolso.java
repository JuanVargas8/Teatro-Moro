package cl.teatromoro.pagos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reembolsos")
@Data
public class Reembolso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idTransaccion;
    private String motivo;
    private LocalDateTime fecha;
    private Double montoDevuelto;
}