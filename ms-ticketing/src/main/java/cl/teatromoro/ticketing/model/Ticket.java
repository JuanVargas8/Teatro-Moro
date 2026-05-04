package cl.teatromoro.ticketing.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_funcion", nullable = false)
    private Integer idFuncion;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "precio_final", nullable = false)
    private BigDecimal precioFinal;

    @ManyToOne
    @JoinColumn(name = "id_tipo_entrada", nullable = false)
    private TipoEntrada tipoEntrada;

    @OneToMany(mappedBy = "ticket")
    private List<HistorialEmision> historial;
}