package cl.teatromoro.funciones.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.teatromoro.funciones.model.entity.Funcion;

public interface FuncionRepository extends JpaRepository<Funcion, Long> {

    List<Funcion> findByIdObra(Long idObra);

    List<Funcion> findByIdSala(Long idSala);

    List<Funcion> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);

}
