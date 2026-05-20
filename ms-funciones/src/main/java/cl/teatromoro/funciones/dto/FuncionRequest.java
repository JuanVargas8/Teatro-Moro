package cl.teatromoro.funciones.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FuncionRequest {

    private Long obraId;   
    private Long salaId;   

    private LocalDateTime fechaHora;
    private Double precioBase;
}
