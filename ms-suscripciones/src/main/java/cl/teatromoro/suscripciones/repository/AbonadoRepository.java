package cl.teatromoro.suscripciones.repository;

import cl.teatromoro.suscripciones.model.Abonado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbonadoRepository extends JpaRepository<Abonado, Long> {
    List<Abonado> findByUsuarioId(Long usuarioId);
}