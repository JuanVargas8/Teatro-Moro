package cl.teatromoro.notificaciones.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColaEnvioResponse {
    private Long id;
    private Integer idUsuario;
    private String estado;
    private Integer reintentos;


}
