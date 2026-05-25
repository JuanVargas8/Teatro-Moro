package cl.teatromoro.usuarios.event;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEventDTO {

    private Long id;
    private String nombre;
    private String correo;
    private String estado;
}