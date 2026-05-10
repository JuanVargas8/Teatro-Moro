package cl.teatromoro.reserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.teatromoro.reserva.model.EstadoAsiento;

import java.util.List;

@Repository
public interface EstadoAsientoRepository extends JpaRepository<EstadoAsiento, Long> {

    List<EstadoAsiento> findByIdFuncion(Integer idFuncion);

    List<EstadoAsiento> findByEstado(String estado);

    List<EstadoAsiento> findByAsientoId(Long asientoId);

}