package cl.teatromoro.promociones.repository;

import cl.teatromoro.promociones.model.ProgramaLealtad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramaLealtadRepository extends JpaRepository<ProgramaLealtad, Long> {
}