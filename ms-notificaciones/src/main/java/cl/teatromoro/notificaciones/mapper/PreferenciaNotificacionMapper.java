package cl.teatromoro.notificaciones.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.notificaciones.dto.PreferenciaNotificacionRequest;
import cl.teatromoro.notificaciones.dto.PreferenciaNotificacionResponse;
import cl.teatromoro.notificaciones.model.PreferenciaNotificacion;

@Component
public class PreferenciaNotificacionMapper {
    public PreferenciaNotificacion toEntity(PreferenciaNotificacionRequest request){
        return PreferenciaNotificacion.builder()
        .idUsuario(request.getIdUsuario())
        .email(request.getEmail())
        .sms(request.getSms())
        .build();
    }

    public PreferenciaNotificacionResponse toResponse(PreferenciaNotificacion entity){
        return PreferenciaNotificacionResponse.builder()
        .idUsuario(entity.getIdUsuario())
        .email(entity.getEmail())
        .sms(entity.getSms())
        .build();
    }

}
