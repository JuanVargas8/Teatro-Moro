package cl.teatromoro.reserva.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Mapa_Asientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapaAsiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_sala", nullable = false)
    private Integer idSala;

    @Column(name = "fila", nullable = false, length = 5)
    private String fila;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "coord_x", nullable = false)
    private Integer coordX;

    @OneToMany(mappedBy = "asiento")
    private List<EstadoAsiento> estados;

    @OneToMany(mappedBy = "asiento")
    private List<BloqueoTemporal> bloqueos;
}