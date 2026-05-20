package cl.teatromoro.funciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.common.exception.EntityNotFoundException;
import cl.teatromoro.funciones.dto.ExcepcionHorarioRequest;
import cl.teatromoro.funciones.dto.ExcepcionHorarioResponse;
import cl.teatromoro.funciones.mapper.ExcepcionHorarioMapper;
import cl.teatromoro.funciones.model.entity.ExcepcionHorario;
import cl.teatromoro.funciones.repository.ExcepcionHorarioRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ExcepcionHorarioService {

    private final ExcepcionHorarioRepository repository;
    private final ExcepcionHorarioMapper mapper;

    // ─── LISTAR ─────────────────────────────────────────

    public List<ExcepcionHorarioResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER ────────────────────────────────────────

    public ExcepcionHorarioResponse obtenerPorId(Long id) {
        ExcepcionHorario ex = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ExcepcionHorario", "id", id));

        return mapper.toResponse(ex);
    }

    // ─── CREAR ──────────────────────────────────────────

    public ExcepcionHorarioResponse guardar(ExcepcionHorarioRequest request) {
        ExcepcionHorario ex = mapper.toEntity(request);
        return mapper.toResponse(repository.save(ex));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        ExcepcionHorario ex = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ExcepcionHorario", "id", id));

        repository.delete(ex);
    }
}
