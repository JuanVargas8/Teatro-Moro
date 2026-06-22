package cl.teatromoro.funciones.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FuncionResponse {

    private Long id;
    private Long obraId;
    private Long salaId;

    private LocalDateTime fechaHora;
    private Double precioBase;
    
    // Datos enriquecidos desde otros microservicios mediante Feign
    private java.util.Map<String, Object> obra;
    private java.util.Map<String, Object> sala;
}
