package cl.teatromoro.notificaciones.dto;

import lombok.Data;

@Data
public class PreferenciaNotificacionRequest {

    private Integer idUsuario;
    private Boolean email;
    private Boolean sms;

}
