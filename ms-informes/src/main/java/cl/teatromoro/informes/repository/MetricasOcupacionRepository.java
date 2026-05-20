package cl.teatromoro.informes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.teatromoro.informes.model.entity.MetricasOcupacion;

import java.util.List;

public interface MetricasOcupacionRepository extends JpaRepository<MetricasOcupacion, Long> {
    List<MetricasOcupacion> findByIdFuncion(Long idFuncion);
    boolean existsByIdFuncion(Long idFuncion);
}
