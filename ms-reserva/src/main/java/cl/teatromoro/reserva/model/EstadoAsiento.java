package cl.teatromoro.reserva.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Estado_Asientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoAsiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_funcion", nullable = false)
    private Integer idFuncion;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_asiento", nullable = false)
    private MapaAsiento asiento;
}