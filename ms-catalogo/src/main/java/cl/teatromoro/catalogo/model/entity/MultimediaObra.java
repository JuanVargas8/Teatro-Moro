package cl.teatromoro.catalogo.model.entity;

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
@Table(name = "multimedia_obra")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MultimediaObra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlImagen;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_obra")
    private Obra obra;
}
