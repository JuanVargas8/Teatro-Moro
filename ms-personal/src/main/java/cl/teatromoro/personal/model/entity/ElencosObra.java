package cl.teatromoro.personal.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "elencos_obra")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElencosObra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="ID_Obra")
    private Long idObra;

    @Column(name="ID_Personal")
    private Long idPersonal;

    @Column(name="Rol_En_Obra")
    private String rolEnObra;
}
