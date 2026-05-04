package cl.teatromoro.reserva.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BloqueoTemporalRequest {
    private String idSesionUsuario;
    private LocalDateTime expiracion;


}
