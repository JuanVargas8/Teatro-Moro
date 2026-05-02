package cl.teatromoro.funciones.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "turnos_funcion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurnoFuncion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idPersonalCargo; // otro MS

    @ManyToOne
    @JoinColumn(name = "id_funcion")
    private Funcion funcion;
}
