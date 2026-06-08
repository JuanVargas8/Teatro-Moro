package cl.teatromoro.ticketing.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Historial_Emisiones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialEmision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de emisión es obligatoria")
    @PastOrPresent(message = "La fecha de emisión no puede ser futura")
    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @NotBlank(message = "El canal de venta es obligatorio")
    @Column(name = "canal_venta", nullable = false, length = 20)
    private String canalVenta;

    @NotNull(message = "El ticket es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_ticket", nullable = false)
    private Ticket ticket;
}