package cl.teatromoro.informes.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "snapshot_ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder    
public class SnapshotVentas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name="Fecha")
    private LocalDate fecha;

    @Column (name = "Total_Recaudado")
    private Double totalRecaudado;

    @Column (name = "Total_Tickets")
    private Integer totalTickets;
}
