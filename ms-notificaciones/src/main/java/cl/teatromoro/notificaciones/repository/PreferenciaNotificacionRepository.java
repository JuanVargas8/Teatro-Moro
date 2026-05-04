package cl.teatromoro.notificaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

import cl.teatromoro.notificaciones.model.PreferenciaNotificacion;

@Repository
public interface PreferenciaNotificacionRepository extends JpaRepository<PreferenciaNotificacion, Integer> {

    Optional<PreferenciaNotificacion> findByIdUsuario(Integer idUsuario);

    List<PreferenciaNotificacion> findByEmail(Boolean email);

    List<PreferenciaNotificacion> findBySms(Boolean sms);

    List<PreferenciaNotificacion> findByEmailAndSms(Boolean email, Boolean sms);
}