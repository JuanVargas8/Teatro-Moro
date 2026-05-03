package cl.teatromoro.notificaciones.model;

import jakarta.persistence.*;
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
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "email", nullable = false)
    private Boolean email;

    @Column(name = "sms", nullable = false)
    private Boolean sms;
}