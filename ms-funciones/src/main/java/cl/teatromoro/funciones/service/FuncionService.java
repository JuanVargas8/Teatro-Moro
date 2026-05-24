package cl.teatromoro.funciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.common.exception.EntityNotFoundException;
import cl.teatromoro.funciones.dto.FuncionRequest;
import cl.teatromoro.funciones.dto.FuncionResponse;
import cl.teatromoro.funciones.mapper.FuncionMapper;
import cl.teatromoro.funciones.model.entity.Funcion;
import cl.teatromoro.funciones.repository.FuncionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionService {

    private final FuncionRepository repository;
    private final FuncionMapper mapper;
    private final cl.teatromoro.funciones.client.FuncionClient funcionClient;
    private final cl.teatromoro.funciones.client.TurnoFuncionClient turnoFuncionClient;
    private final cl.teatromoro.funciones.event.FuncionEventProducer eventProducer;

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
        // Validación Feign: Comprobar que la obra y la sala existen en los otros microservicios
        // Si no existen o el ms está caído, Feign lanzará una excepción que será atrapada
        // por el GlobalExceptionHandler mostrando el error en Postman.
        funcionClient.getObraById(request.getObraId());
        turnoFuncionClient.getSalaById(request.getSalaId());

        Funcion funcion = mapper.toEntity(request);
        Funcion guardada = repository.save(funcion);

        cl.teatromoro.common.event.FuncionCreatedEvent event = new cl.teatromoro.common.event.FuncionCreatedEvent();
        event.setId(guardada.getId());
        event.setPeliculaId(guardada.getIdObra());
        event.setSalaId(guardada.getIdSala());
        event.setFechaHora(guardada.getFechaHora());
        event.setPrecio(guardada.getPrecioBase());
        eventProducer.sendCreated(event);

        return mapper.toResponse(guardada);
    }

    // ─── ACTUALIZAR ─────────────────────────────────────

    public FuncionResponse actualizar(Long id, FuncionRequest request) {
        // Validación Feign: Comprobar que la obra y la sala existen
        funcionClient.getObraById(request.getObraId());
        turnoFuncionClient.getSalaById(request.getSalaId());

        Funcion existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcion", "id", id));

        existente.setFechaHora(request.getFechaHora());
        existente.setPrecioBase(request.getPrecioBase());
        existente.setIdObra(request.getObraId());
        existente.setIdSala(request.getSalaId());

        Funcion guardada = repository.save(existente);

        cl.teatromoro.common.event.FuncionUpdatedEvent event = new cl.teatromoro.common.event.FuncionUpdatedEvent();
        event.setId(guardada.getId());
        event.setPeliculaId(guardada.getIdObra());
        event.setSalaId(guardada.getIdSala());
        event.setFechaHora(guardada.getFechaHora());
        event.setPrecio(guardada.getPrecioBase());
        eventProducer.sendUpdated(event);

        return mapper.toResponse(guardada);
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        Funcion funcion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcion", "id", id));

        repository.delete(funcion);

        cl.teatromoro.common.event.FuncionDeletedEvent event = new cl.teatromoro.common.event.FuncionDeletedEvent();
        event.setId(id);
        eventProducer.sendDeleted(event);
    }

    // ─── POR OBRA ───────────────────────────────────────

    public List<FuncionResponse> porObra(Long obraId) {
        // Validación Feign: Comprobar que la obra existe en ms-catalogo
        funcionClient.getObraById(obraId);

        return repository.findByIdObra(obraId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── POR SALA ───────────────────────────────────────

    public List<FuncionResponse> porSala(Long salaId) {
        // Validación Feign: Comprobar que la sala existe en ms-gestion
        turnoFuncionClient.getSalaById(salaId);

        return repository.findByIdSala(salaId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
