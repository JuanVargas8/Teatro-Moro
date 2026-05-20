package cl.teatromoro.gestion.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.teatromoro.gestion.model.entity.MantenimientoSala;

public interface MantenimientoSalaRepository extends JpaRepository<MantenimientoSala, Long> {

    List<MantenimientoSala> findBySalaId(Long salaId);

    List<MantenimientoSala> findByFechaInicioBetween(LocalDate inicio, LocalDate fin);

    List<MantenimientoSala> findBySalaIdAndFechaInicioBetween(Long salaId, LocalDate inicio, LocalDate fin);
}
