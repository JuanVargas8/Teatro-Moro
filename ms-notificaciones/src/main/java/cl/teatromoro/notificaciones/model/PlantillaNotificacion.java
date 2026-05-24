package cl.teatromoro.notificaciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(
    name = "Plantillas_Notificacion",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tipo"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlantillaNotificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El tipo es obligatorio")
    @Column(name = "tipo", nullable = false, length = 50, unique = true)
    private String tipo;

    @NotBlank(message = "El cuerpo es obligatorio")
    @Lob
    @Column(name = "cuerpo", nullable = false)
    private String cuerpo;

    @OneToMany(mappedBy = "plantilla")
    private List<ColaEnvio> envios;
}