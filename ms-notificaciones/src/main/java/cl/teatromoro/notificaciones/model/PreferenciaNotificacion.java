package cl.teatromoro.notificaciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "Preferencias_Notificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreferenciaNotificacion {

    @Id
    @NotNull(message = "El usuario es obligatorio")
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @NotNull(message = "La preferencia de email es obligatoria")
    @Column(name = "email", nullable = false)
    private Boolean email;

    @NotNull(message = "La preferencia de sms es obligatoria")
    @Column(name = "sms", nullable = false)
    private Boolean sms;
}