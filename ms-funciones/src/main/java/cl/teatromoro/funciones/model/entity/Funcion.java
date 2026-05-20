package cl.teatromoro.funciones.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK lógicas
    private Long idObra;
    private Long idSala;

    private LocalDateTime fechaHora;

    private Double precioBase;
}
