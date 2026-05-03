package cl.teatromoro.reserva.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Bloqueos_Temporales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloqueoTemporal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_sesion_usuario", nullable = false, length = 50)
    private String idSesionUsuario;

    @Column(name = "expiracion", nullable = false)
    private LocalDateTime expiracion;

    @ManyToOne
    @JoinColumn(name = "id_asiento", nullable = false)
    private MapaAsiento asiento;
}