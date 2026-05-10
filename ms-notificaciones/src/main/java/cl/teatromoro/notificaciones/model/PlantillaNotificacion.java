package cl.teatromoro.notificaciones.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Plantillas_Notificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlantillaNotificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Lob
    @Column(name = "cuerpo", nullable = false)
    private String cuerpo;

    @OneToMany(mappedBy = "plantilla")
    private List<ColaEnvio> envios;
}