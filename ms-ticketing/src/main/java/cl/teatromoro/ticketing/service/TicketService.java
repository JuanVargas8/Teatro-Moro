package cl.teatromoro.ticketing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.ticketing.client.FuncionesClient;
import cl.teatromoro.ticketing.client.UsuarioClient;
import cl.teatromoro.ticketing.dto.TicketRequest;
import cl.teatromoro.ticketing.dto.TicketResponse;
import cl.teatromoro.ticketing.exception.ResourceNotFoundException;
import cl.teatromoro.ticketing.mapper.TicketMapper;
import cl.teatromoro.ticketing.model.Ticket;
import cl.teatromoro.ticketing.model.TipoEntrada;
import cl.teatromoro.ticketing.repository.TicketRepository;
import cl.teatromoro.ticketing.repository.TipoEntradaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repository;
    private final TipoEntradaRepository tipoEntradaRepository;
    private final TicketMapper mapper;
    private final UsuarioClient usuarioClient;
    private final FuncionesClient funcionesClient;

    // ─── LISTAR ─────────────────────────────────────────

    public List<TicketResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER ────────────────────────────────────────

    public TicketResponse obtenerPorId(Long id) {
        Ticket entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", id));

        return mapper.toResponse(entity);
    }

    public TicketResponse guardar(TicketRequest request) {

        usuarioClient.obtenerUsuario(request.getIdUsuario());

        funcionesClient.obtenerFuncion(request.getIdFuncion());

        TipoEntrada tipoEntrada = tipoEntradaRepository.findById(request.getIdTipoEntrada())
                .orElseThrow(() -> new ResourceNotFoundException("TipoEntrada", request.getIdTipoEntrada()));

        Ticket entity = mapper.toEntity(request);
        entity.setTipoEntrada(tipoEntrada);

        return mapper.toResponse(repository.save(entity));
    }
    // ─── ACTUALIZAR ─────────────────────────────────────

    public TicketResponse actualizar(Long id, TicketRequest request) {

        Ticket existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", id));

        usuarioClient.obtenerUsuario(request.getIdUsuario());

        funcionesClient.obtenerFuncion(request.getIdFuncion());

        TipoEntrada tipoEntrada = tipoEntradaRepository.findById(request.getIdTipoEntrada())
                .orElseThrow(() -> new ResourceNotFoundException("TipoEntrada", request.getIdTipoEntrada()));

        existente.setIdFuncion(request.getIdFuncion());
        existente.setIdUsuario(request.getIdUsuario());
        existente.setPrecioFinal(request.getPrecioFinal());
        existente.setTipoEntrada(tipoEntrada);

        return mapper.toResponse(repository.save(existente));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        Ticket entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", id));

        repository.delete(entity);
    }

    // ─── FILTRO POR USUARIO ────────────────────────────

    public List<TicketResponse> porUsuario(Integer idUsuario) {
        return repository.findByIdUsuario(idUsuario)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── FILTRO POR FUNCION ────────────────────────────

    public List<TicketResponse> porFuncion(Integer idFuncion) {
        return repository.findByIdFuncion(idFuncion)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}