package cl.teatromoro.promociones.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Promocion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private Double porcentaje;
    private String descripcion;
}