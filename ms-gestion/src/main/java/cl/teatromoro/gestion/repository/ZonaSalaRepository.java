package cl.teatromoro.gestion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.teatromoro.gestion.model.entity.ZonaSala;

public interface ZonaSalaRepository extends JpaRepository<ZonaSala, Long>{

    List<ZonaSala> findBySalaId(Long salaId);

    boolean existsByNombreIgnoreCaseAndSalaId(String nombre, Long salaId);
}
