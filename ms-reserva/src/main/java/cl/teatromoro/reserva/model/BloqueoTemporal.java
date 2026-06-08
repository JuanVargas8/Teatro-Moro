package cl.teatromoro.reserva.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "La sesión de usuario es obligatoria")
    @Column(name = "id_sesion_usuario", nullable = false, length = 50)
    private String idSesionUsuario;

    @NotNull(message = "La fecha de expiración es obligatoria")
    @Future(message = "La expiración debe ser una fecha futura")
    @Column(name = "expiracion", nullable = false)
    private LocalDateTime expiracion;

    @NotNull(message = "El asiento es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_asiento", nullable = false)
    private MapaAsiento asiento;
}