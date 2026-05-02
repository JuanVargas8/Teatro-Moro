package cl.teatromoro.funciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.teatromoro.funciones.model.entity.TurnoFuncion;

public interface TurnoFuncionRepository extends JpaRepository<TurnoFuncion, Long>{

    List<TurnoFuncion> findByFuncionId(Long funcionId);

    List<TurnoFuncion> findByIdPersonalCargo(Long idPersonalCargo);

}
