package cl.teatromoro.funciones.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class FuncionResponse extends RepresentationModel<FuncionResponse> {

    private Long id;
    private Long obraId;
    private Long salaId;

    private LocalDateTime fechaHora;
    private Double precioBase;
    
    // Datos enriquecidos desde otros microservicios mediante Feign
    private java.util.Map<String, Object> obra;
    private java.util.Map<String, Object> sala;
}
