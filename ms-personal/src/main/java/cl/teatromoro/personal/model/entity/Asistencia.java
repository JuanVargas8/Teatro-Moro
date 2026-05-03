package cl.teatromoro.personal.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "asistencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="ID_Personal")
    private Long idPersonal;

    @Column(name="ID_Funcion")
    private Long idFuncion;

    @Column(name="Hora_Entrada")
    private LocalDateTime horaEntrada;
}
