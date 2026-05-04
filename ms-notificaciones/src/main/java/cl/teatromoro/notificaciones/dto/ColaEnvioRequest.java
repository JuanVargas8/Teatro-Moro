package cl.teatromoro.notificaciones.dto;

import lombok.Data;

@Data
public class ColaEnvioRequest {
    private Integer idUsuario;
    private String estado;
    private Integer reintentos;

}
