package cl.teatromoro.funciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.teatromoro.funciones.model.entity.ExcepcionHorario;

public interface ExcepcionHorarioRepository extends JpaRepository<ExcepcionHorario, Long>{
    
}
