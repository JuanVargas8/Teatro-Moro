package cl.teatromoro.reserva.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.reserva.dto.EstadoAsientoRequest;
import cl.teatromoro.reserva.dto.EstadoAsientoResponse;
import cl.teatromoro.reserva.exception.ResourceNotFoundException;
import cl.teatromoro.reserva.mapper.EstadoAsientoMapper;
import cl.teatromoro.reserva.model.EstadoAsiento;
import cl.teatromoro.reserva.repository.EstadoAsientoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstadoAsientoService {

    private final EstadoAsientoRepository repository;
    private final EstadoAsientoMapper mapper;

    // ─── LISTAR ─────────────────────────────────────────

    public List<EstadoAsientoResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER ────────────────────────────────────────

    public EstadoAsientoResponse obtenerPorId(Long id) {
        EstadoAsiento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoAsiento", id));

        return mapper.toResponse(entity);
    }

    // ─── CREAR ──────────────────────────────────────────

    public EstadoAsientoResponse guardar(EstadoAsientoRequest request) {
        EstadoAsiento entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    // ─── ACTUALIZAR ─────────────────────────────────────

    public EstadoAsientoResponse actualizar(Long id, EstadoAsientoRequest request) {
        EstadoAsiento existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoAsiento", id));

        existente.setIdFuncion(request.getIdFuncion());
        existente.setEstado(request.getEstado());

        return mapper.toResponse(repository.save(existente));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        EstadoAsiento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoAsiento", id));

        repository.delete(entity);
    }

    // ─── FILTRO POR FUNCION ────────────────────────────

    public List<EstadoAsientoResponse> porFuncion(Integer idFuncion) {
        return repository.findByIdFuncion(idFuncion)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── FILTRO POR ESTADO ─────────────────────────────

    public List<EstadoAsientoResponse> porEstado(String estado) {
        return repository.findByEstado(estado)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}