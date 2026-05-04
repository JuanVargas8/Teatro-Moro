package cl.teatromoro.ticketing.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.teatromoro.ticketing.model.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByIdFuncion(Integer idFuncion);

    List<Ticket> findByIdUsuario(Integer idUsuario);

    List<Ticket> findByTipoEntradaId(Long tipoEntradaId);

    List<Ticket> findByIdFuncionAndIdUsuario(Integer idFuncion, Integer idUsuario);

}