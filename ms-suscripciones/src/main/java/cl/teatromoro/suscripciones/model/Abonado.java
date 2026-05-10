package cl.teatromoro.suscripciones.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Abonado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId; // 👈 clave (relación entre microservicios)
    private Long planId;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}