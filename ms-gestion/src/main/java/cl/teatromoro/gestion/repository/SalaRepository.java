package cl.teatromoro.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.teatromoro.gestion.model.entity.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long>{

    boolean existsByNombreIgnoreCase(String nombre);
}
