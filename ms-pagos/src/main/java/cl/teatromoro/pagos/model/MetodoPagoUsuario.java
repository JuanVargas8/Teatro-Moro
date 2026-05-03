package cl.teatromoro.pagos.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "metodos_pago_usuario")
@Data
public class MetodoPagoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idUsuario;
    private String tokenPasarela;
    private String ultimos4Digitos;
}