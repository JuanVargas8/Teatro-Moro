package cl.teatromoro.informes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.teatromoro.informes.model.entity.TendenciasBusqueda;

import java.time.LocalDate;
import java.util.List;

public interface TendenciasBusquedaRepository extends JpaRepository<TendenciasBusqueda, Long> {
    List<TendenciasBusqueda> findByFecha(LocalDate fecha);
    List<TendenciasBusqueda> findByTerminoBusquedaIgnoreCase(String terminoBusqueda);
    boolean existsByTerminoBusquedaIgnoreCaseAndFecha(String terminoBusqueda, LocalDate fecha);
}
