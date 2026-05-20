package cl.teatromoro.promociones.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class ProgramaLealtad {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreNivel;
    private Integer puntosMinimos;
    private String beneficio;
}