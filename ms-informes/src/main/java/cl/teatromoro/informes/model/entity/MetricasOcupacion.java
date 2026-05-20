package cl.teatromoro.informes.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "metricas_ocupacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetricasOcupacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "ID_Funcion")
    private Long idFuncion;

    @Column (name="Porcentaje_Llenado")
    private Double porcentajeLlenado;

}
