package cl.teatromoro.notificaciones.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PreferenciaNotificacionResponse {
    private Integer idUsuario;
    private Boolean email;
    private Boolean sms;
}
