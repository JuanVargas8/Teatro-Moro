package cl.teatromoro.reserva.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "La función es obligatoria")
    @Column(name = "id_funcion", nullable = false)
    private Integer idFuncion;

    @NotBlank(message = "El estado es obligatorio")
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @NotNull(message = "El asiento es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_asiento", nullable = false)
    private MapaAsiento asiento;
}