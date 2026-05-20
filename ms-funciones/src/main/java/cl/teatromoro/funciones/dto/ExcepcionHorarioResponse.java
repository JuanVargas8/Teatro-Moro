package cl.teatromoro.funciones.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExcepcionHorarioResponse {

    private Long id;
    private LocalDate fecha;
    private String motivo;
}
