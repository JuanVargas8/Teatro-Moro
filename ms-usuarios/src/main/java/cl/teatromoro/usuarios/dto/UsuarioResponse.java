package cl.teatromoro.usuarios.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioResponse {
    private Long id;
    private String email;
    private String nombre;
    private String rol;
    private LocalDate fechaRegistro;
}
