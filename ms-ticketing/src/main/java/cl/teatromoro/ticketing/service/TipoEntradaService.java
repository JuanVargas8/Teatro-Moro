package cl.teatromoro.ticketing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.ticketing.dto.TipoEntradaRequest;
import cl.teatromoro.ticketing.dto.TipoEntradaResponse;
import cl.teatromoro.ticketing.exception.ResourceNotFoundException;
import cl.teatromoro.ticketing.mapper.TipoEntradaMapper;
import cl.teatromoro.ticketing.model.TipoEntrada;
import cl.teatromoro.ticketing.repository.TipoEntradaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoEntradaService {

    private final TipoEntradaRepository repository;
    private final TipoEntradaMapper mapper;

    // ─── LISTAR ─────────────────────────────────────────

    public List<TipoEntradaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER ────────────────────────────────────────

    public TipoEntradaResponse obtenerPorId(Long id) {
        TipoEntrada entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoEntrada", id));

        return mapper.toResponse(entity);
    }

    // ─── CREAR ──────────────────────────────────────────

    public TipoEntradaResponse guardar(TipoEntradaRequest request) {
        TipoEntrada entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    // ─── ACTUALIZAR ─────────────────────────────────────

    public TipoEntradaResponse actualizar(Long id, TipoEntradaRequest request) {
        TipoEntrada existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoEntrada", id));

        existente.setNombre(request.getNombre());
        existente.setDescuento(request.getDescuento());

        return mapper.toResponse(repository.save(existente));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        TipoEntrada entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoEntrada", id));

        repository.delete(entity);
    }
}