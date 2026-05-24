package cl.teatromoro.reserva.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(
    name = "Mapa_Asientos",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_sala", "fila", "numero"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapaAsiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La sala es obligatoria")
    @Column(name = "id_sala", nullable = false)
    private Integer idSala;

    @NotBlank(message = "La fila es obligatoria")
    @Column(name = "fila", nullable = false, length = 5)
    private String fila;

    @NotNull(message = "El número de asiento es obligatorio")
    @Min(value = 1, message = "El número debe ser mayor a 0")
    @Column(name = "numero", nullable = false)
    private Integer numero;

    @NotNull(message = "La coordenada X es obligatoria")
    @Column(name = "coord_x", nullable = false)
    private Integer coordX;

    @OneToMany(mappedBy = "asiento")
    private List<EstadoAsiento> estados;

    @OneToMany(mappedBy = "asiento")
    private List<BloqueoTemporal> bloqueos;
}