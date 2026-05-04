package cl.teatromoro.notificaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.teatromoro.notificaciones.model.ColaEnvio;

import java.util.List;

@Repository
public interface ColaEnvioRepository extends JpaRepository<ColaEnvio, Long> {

    List<ColaEnvio> findByIdUsuario(Integer idUsuario);

    List<ColaEnvio> findByEstado(String estado);

    List<ColaEnvio> findByPlantillaId(Long plantillaId);

    List<ColaEnvio> findByEstadoAndIdUsuario(String estado, Integer idUsuario);

}