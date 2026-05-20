package cl.teatromoro.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.teatromoro.personal.model.entity.Asistencia;

import java.time.LocalDateTime;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByIdPersonal(Long idPersonal);
    List<Asistencia> findByIdFuncion(Long idFuncion);
    boolean existsByIdPersonalAndIdFuncionAndHoraEntrada(Long idPersonal, Long idFuncion, LocalDateTime horaEntrada);
}
