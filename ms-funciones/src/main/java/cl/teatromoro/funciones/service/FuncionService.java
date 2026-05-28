package cl.teatromoro.funciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.common.exception.EntityNotFoundException;
import cl.teatromoro.funciones.client.FuncionClient;
import cl.teatromoro.funciones.client.TurnoFuncionClient;
import cl.teatromoro.funciones.dto.FuncionRequest;
import cl.teatromoro.funciones.dto.FuncionResponse;
import cl.teatromoro.funciones.mapper.FuncionMapper;
import cl.teatromoro.funciones.model.entity.Funcion;
import cl.teatromoro.funciones.repository.FuncionRepository;
import cl.teatromoro.common.event.FuncionCreatedEvent;
import cl.teatromoro.common.event.FuncionUpdatedEvent;
import cl.teatromoro.common.event.FuncionDeletedEvent;
import cl.teatromoro.funciones.event.FuncionEventProducer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionService {

    private final FuncionRepository repository;
    private final FuncionMapper mapper;
    private final FuncionClient funcionClient;
    private final TurnoFuncionClient turnoFuncionClient;
    private final FuncionEventProducer funcionEventProducer;

    // ─── LISTAR ─────────────────────────────────────────

    public List<FuncionResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER ────────────────────────────────────────

    public FuncionResponse obtenerPorId(Long id) {
        Funcion funcion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcion", "id", id));

        return mapper.toResponse(funcion);
    }

    // ─── CREAR ──────────────────────────────────────────

    public FuncionResponse guardar(FuncionRequest request) {
        // Validar existencia de la Obra y la Sala mediante Feign Clients
        funcionClient.getObraById(request.getObraId());
        turnoFuncionClient.getSalaById(request.getSalaId());

        Funcion funcion = mapper.toEntity(request);
        funcion = repository.save(funcion);
        
        FuncionCreatedEvent event = new FuncionCreatedEvent();
        event.setId(funcion.getId());
        event.setPeliculaId(funcion.getIdObra());
        event.setSalaId(funcion.getIdSala());
        event.setFechaHora(funcion.getFechaHora());
        event.setPrecio(funcion.getPrecioBase());
        funcionEventProducer.sendCreated(event);
        
        return mapper.toResponse(funcion);
    }

    // ─── ACTUALIZAR ─────────────────────────────────────

    public FuncionResponse actualizar(Long id, FuncionRequest request) {
        // Validar existencia de la Obra y la Sala mediante Feign Clients
        funcionClient.getObraById(request.getObraId());
        turnoFuncionClient.getSalaById(request.getSalaId());

        Funcion existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcion", "id", id));

        existente.setFechaHora(request.getFechaHora());
        existente.setPrecioBase(request.getPrecioBase());
        existente.setIdObra(request.getObraId());
        existente.setIdSala(request.getSalaId());

        existente = repository.save(existente);
        
        FuncionUpdatedEvent event = new FuncionUpdatedEvent();
        event.setId(existente.getId());
        event.setPeliculaId(existente.getIdObra());
        event.setSalaId(existente.getIdSala());
        event.setFechaHora(existente.getFechaHora());
        event.setPrecio(existente.getPrecioBase());
        funcionEventProducer.sendUpdated(event);

        return mapper.toResponse(existente);
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        Funcion funcion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcion", "id", id));

        repository.delete(funcion);
        
        FuncionDeletedEvent event = new FuncionDeletedEvent();
        event.setId(id);
        funcionEventProducer.sendDeleted(event);
    }

    // ─── POR OBRA ───────────────────────────────────────

    public List<FuncionResponse> porObra(Long obraId) {
        return repository.findByIdObra(obraId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── POR SALA ───────────────────────────────────────

    public List<FuncionResponse> porSala(Long salaId) {
        return repository.findByIdSala(salaId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
