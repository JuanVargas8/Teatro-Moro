package cl.teatromoro.informes.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tendencias_busqueda")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TendenciasBusqueda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Termino_Busqueda")
    private String terminoBusqueda;

    @Column (name = "Cantidad_Resultados")
    private Integer cantidadResultados;

    @Column(name ="Fecha")
    private LocalDate fecha;
}
