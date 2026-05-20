package cl.teatromoro.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.teatromoro.personal.model.entity.Personal;

import java.util.List;

public interface PersonalRepository extends JpaRepository<Personal, Long> {
    List<Personal> findByEspecialidadIgnoreCase(String especialidad);
    boolean existsByNombreIgnoreCase(String nombre);
}
