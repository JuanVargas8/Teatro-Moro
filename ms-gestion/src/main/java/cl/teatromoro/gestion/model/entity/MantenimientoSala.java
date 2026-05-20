package cl.teatromoro.gestion.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Mantenimiento_Salas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MantenimientoSala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Fecha_Inicio")
    private LocalDate fechaInicio;

    @Column(name = "Fecha_Fin")
    private LocalDate fechaFin;

    @Column(name = "Descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "ID_Sala")
    private Sala sala;
}
