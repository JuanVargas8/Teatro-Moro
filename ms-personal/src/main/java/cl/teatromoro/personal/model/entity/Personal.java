package cl.teatromoro.personal.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "personal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Nombre")
    private String nombre;

    @Column(name="Especialidad")
    private String especialidad; // Actor, Técnico

    @Column(name="Tipo_Contrato")
    private String tipoContrato;
}
