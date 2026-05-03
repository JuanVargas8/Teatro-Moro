package cl.teatromoro.personal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonalRequest {
    private String nombre;
    private String especialidad;   // Actor, Técnico
    private String tipoContrato;   // Ej: Plazo fijo, Honorarios
}
