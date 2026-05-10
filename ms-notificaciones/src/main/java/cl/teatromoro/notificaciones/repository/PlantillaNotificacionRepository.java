package cl.teatromoro.notificaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.teatromoro.notificaciones.model.PlantillaNotificacion;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantillaNotificacionRepository extends JpaRepository<PlantillaNotificacion, Long> {

    Optional<PlantillaNotificacion> findByTipo(String tipo);

    List<PlantillaNotificacion> findByTipoContaining(String tipo);

}