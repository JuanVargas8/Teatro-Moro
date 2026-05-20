package cl.teatromoro.funciones.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExcepcionHorarioRequest {

    private LocalDate fecha;
    private String motivo;
}
