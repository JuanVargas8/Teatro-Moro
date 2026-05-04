package cl.teatromoro.personal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonalResponse {
    private Long id;
    private String nombre;
    private String especialidad;
    private String tipoContrato;
}
