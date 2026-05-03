package cl.teatromoro.promociones.repository;

import cl.teatromoro.promociones.model.Campana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampanaRepository extends JpaRepository<Campana, Long> {
}