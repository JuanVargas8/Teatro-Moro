package cl.teatromoro.reserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.teatromoro.reserva.model.BloqueoTemporal;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BloqueoTemporalRepository extends JpaRepository<BloqueoTemporal, Long> {

    List<BloqueoTemporal> findByAsientoId(Long asientoId);

    List<BloqueoTemporal> findByIdSesionUsuario(String idSesionUsuario);

    List<BloqueoTemporal> findByExpiracionBefore(LocalDateTime fecha);

}