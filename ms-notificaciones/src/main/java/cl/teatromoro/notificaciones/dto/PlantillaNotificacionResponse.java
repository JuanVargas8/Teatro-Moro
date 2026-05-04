package cl.teatromoro.notificaciones.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlantillaNotificacionResponse {
    private Long id;
    private String tipo;
    private String cuerpo;
}
