package cl.teatromoro.reserva.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.reserva.dto.MapaAsientoRequest;
import cl.teatromoro.reserva.dto.MapaAsientoResponse;
import cl.teatromoro.reserva.exception.ResourceNotFoundException;
import cl.teatromoro.reserva.mapper.MapaAsientoMapper;
import cl.teatromoro.reserva.model.MapaAsiento;
import cl.teatromoro.reserva.repository.MapaAsientoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapaAsientoService {

    private final MapaAsientoRepository repository;
    private final MapaAsientoMapper mapper;

    // ─── LISTAR ─────────────────────────────────────────

    public List<MapaAsientoResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER ────────────────────────────────────────

    public MapaAsientoResponse obtenerPorId(Long id) {
        MapaAsiento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MapaAsiento", id));

        return mapper.toResponse(entity);
    }

    // ─── CREAR ──────────────────────────────────────────

    public MapaAsientoResponse guardar(MapaAsientoRequest request) {
        MapaAsiento entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    // ─── ACTUALIZAR ─────────────────────────────────────

    public MapaAsientoResponse actualizar(Long id, MapaAsientoRequest request) {
        MapaAsiento existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MapaAsiento", id));

        existente.setIdSala(request.getIdSala());
        existente.setFila(request.getFila());
        existente.setNumero(request.getNumero());
        existente.setCoordX(request.getCoordX());

        return mapper.toResponse(repository.save(existente));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        MapaAsiento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MapaAsiento", id));

        repository.delete(entity);
    }

    // ─── FILTRO POR SALA ───────────────────────────────

    public List<MapaAsientoResponse> porSala(Integer idSala) {
        return repository.findByIdSala(idSala)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── FILTRO POR FILA ───────────────────────────────

    public List<MapaAsientoResponse> porFila(String fila) {
        return repository.findByFila(fila)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}