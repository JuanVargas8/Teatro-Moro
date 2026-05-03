package cl.teatromoro.ticketing.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
//import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Tipos_Entrada")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descuento", nullable = false)
    private BigDecimal descuento;

    @OneToMany(mappedBy = "tipoEntrada")
    private List<Ticket> tickets;
}