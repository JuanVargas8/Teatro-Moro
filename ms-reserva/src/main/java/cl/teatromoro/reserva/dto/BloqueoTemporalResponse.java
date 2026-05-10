package cl.teatromoro.reserva.dto;



import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BloqueoTemporalResponse {
    private Long id;
    private String idSesionUsuario;
    private LocalDateTime expiracion;
}
