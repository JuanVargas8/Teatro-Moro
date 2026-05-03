package cl.teatromoro.promociones.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity @Data
public class Campana {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}