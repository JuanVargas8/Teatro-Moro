package cl.teatromoro.promociones.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CampanaResponse {
    private Long id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}