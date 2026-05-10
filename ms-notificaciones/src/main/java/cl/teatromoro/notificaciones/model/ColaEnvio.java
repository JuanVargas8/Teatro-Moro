package cl.teatromoro.notificaciones.model;

import jakarta.persistence.*;
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

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "reintentos", nullable = false)
    private Integer reintentos;

    @ManyToOne
    @JoinColumn(name = "id_plantilla", nullable = false)
    private PlantillaNotificacion plantilla;
}