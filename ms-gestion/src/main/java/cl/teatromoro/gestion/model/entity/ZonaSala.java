package cl.teatromoro.gestion.model.entity;

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
@Table(name = "Zonas_Sala")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZonaSala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Multiplicador_Precio")
    private Double multiplicadorPrecio;

    @ManyToOne
    @JoinColumn(name = "ID_Sala")
    private Sala sala;
}
