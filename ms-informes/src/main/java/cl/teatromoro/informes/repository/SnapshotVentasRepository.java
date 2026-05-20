package cl.teatromoro.informes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.teatromoro.informes.model.entity.SnapshotVentas;

import java.time.LocalDate;
import java.util.List;

public interface SnapshotVentasRepository extends JpaRepository<SnapshotVentas, Long> {
    List<SnapshotVentas> findByFecha(LocalDate fecha);
    boolean existsByFecha(LocalDate fecha);
}
