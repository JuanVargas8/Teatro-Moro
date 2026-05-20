package cl.teatromoro.usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Email inválido")
    @NotBlank(message = "El email es obligatorio")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private LocalDate fechaRegistro;
}