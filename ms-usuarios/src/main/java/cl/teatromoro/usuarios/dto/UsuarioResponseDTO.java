package cl.teatromoro.usuarios.dto;

import java.time.LocalDate;

public class UsuarioResponseDTO {

    private Long id;
    private String email;
    private String nombre;
    private LocalDate fechaRegistro;

    public UsuarioResponseDTO(
            Long id,
            String email,
            String nombre,
            LocalDate fechaRegistro) {

        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }
}