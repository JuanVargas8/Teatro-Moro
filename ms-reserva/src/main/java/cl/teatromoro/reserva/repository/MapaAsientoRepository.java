package cl.teatromoro.reserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.teatromoro.reserva.model.MapaAsiento;

import java.util.List;

@Repository
public interface MapaAsientoRepository extends JpaRepository<MapaAsiento, Long> {

    List<MapaAsiento> findByIdSala(Integer idSala);

    List<MapaAsiento> findByFila(String fila);

    List<MapaAsiento> findByNumero(Integer numero);

    List<MapaAsiento> findByIdSalaAndFila(Integer idSala, String fila);

}