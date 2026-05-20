package cl.teatromoro.personal.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AsistenciaResponse {
    private Long id;
    private Long idPersonal;
    private Long idFuncion;
    private LocalDateTime horaEntrada;
}
