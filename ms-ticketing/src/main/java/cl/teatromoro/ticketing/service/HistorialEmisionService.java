package cl.teatromoro.ticketing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.common.exception.*;
import cl.teatromoro.ticketing.exception.ResourceNotFoundException;
import cl.teatromoro.ticketing.dto.HistorialEmisionRequest;
import cl.teatromoro.ticketing.dto.HistorialEmisionResponse;
import cl.teatromoro.ticketing.mapper.HistorialEmisionMapper;
import cl.teatromoro.ticketing.model.HistorialEmision;
import cl.teatromoro.ticketing.model.Ticket;
import cl.teatromoro.ticketing.repository.HistorialEmisionRepository;
import cl.teatromoro.ticketing.repository.TicketRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class HistorialEmisionService {

    private final HistorialEmisionRepository repository;
    private final TicketRepository ticketRepository;
    private final HistorialEmisionMapper mapper;

    // ─── LISTAR ─────────────────────────────────────────

    public List<HistorialEmisionResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER ────────────────────────────────────────

    public HistorialEmisionResponse obtenerPorId(Long id) {
        HistorialEmision entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HistorialEmision", id));
        return mapper.toResponse(entity);
    }
    // ─── CREAR ──────────────────────────────────────────

    public HistorialEmisionResponse guardar(HistorialEmisionRequest request) {

        Ticket ticket = ticketRepository.findById(request.getIdTicket())
                .orElseThrow(() ->    new ResourceNotFoundException("Ticket", request.getIdTicket()));

        HistorialEmision entity = mapper.toEntity(request);
        entity.setTicket(ticket);

        return mapper.toResponse(repository.save(entity));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        HistorialEmision entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HistorialEmision", id));

        repository.delete(entity);
    }

    // ─── POR TICKET ─────────────────────────────────────

    public List<HistorialEmisionResponse> porTicket(Long ticketId) {

        if (!ticketRepository.existsById(ticketId)) {
            throw new ResourceNotFoundException("Ticket", ticketId);
        }

        return repository.findByTicketId(ticketId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── POR CANAL ──────────────────────────────────────

    public List<HistorialEmisionResponse> porCanal(String canal) {
        return repository.findByCanalVenta(canal)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}