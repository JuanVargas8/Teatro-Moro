package cl.teatromoro.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.teatromoro.personal.model.entity.ElencosObra;

import java.util.List;

public interface ElencosObraRepository extends JpaRepository<ElencosObra, Long> {
    List<ElencosObra> findByIdObra(Long idObra);
    List<ElencosObra> findByIdPersonal(Long idPersonal);
    boolean existsByIdObraAndIdPersonal(Long idObra, Long idPersonal);
}
