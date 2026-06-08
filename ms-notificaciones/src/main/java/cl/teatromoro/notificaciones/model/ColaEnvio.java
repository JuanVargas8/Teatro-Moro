package cl.teatromoro.notificaciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "Cola_Envios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColaEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El usuario es obligatorio")
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @NotBlank(message = "El estado es obligatorio")
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @NotNull(message = "Los reintentos son obligatorios")
    @Min(value = 0, message = "Los reintentos no pueden ser negativos")
    @Column(name = "reintentos", nullable = false)
    private Integer reintentos;

    @NotNull(message = "La plantilla es obligatoria")
    @ManyToOne
    @JoinColumn(name = "id_plantilla", nullable = false)
    private PlantillaNotificacion plantilla;
}