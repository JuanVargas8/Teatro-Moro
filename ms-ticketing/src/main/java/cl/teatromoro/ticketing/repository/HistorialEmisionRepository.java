package cl.teatromoro.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.teatromoro.ticketing.model.HistorialEmision;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistorialEmisionRepository extends JpaRepository<HistorialEmision, Long> {

    List<HistorialEmision> findByTicketId(Long ticketId);

    List<HistorialEmision> findByCanalVenta(String canalVenta);

    List<HistorialEmision> findByFechaEmision(LocalDate fechaEmision);

    List<HistorialEmision> findByTicketIdAndCanalVenta(Long ticketId, String canalVenta);

}