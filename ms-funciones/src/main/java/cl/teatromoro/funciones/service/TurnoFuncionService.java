package cl.teatromoro.funciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.common.exception.EntityNotFoundException;
import cl.teatromoro.funciones.dto.TurnoFuncionRequest;
import cl.teatromoro.funciones.dto.TurnoFuncionResponse;
import cl.teatromoro.funciones.mapper.TurnoFuncionMapper;
import cl.teatromoro.funciones.model.entity.Funcion;
import cl.teatromoro.funciones.model.entity.TurnoFuncion;
import cl.teatromoro.funciones.repository.FuncionRepository;
import cl.teatromoro.funciones.repository.TurnoFuncionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TurnoFuncionService {

    private final TurnoFuncionRepository repository;
    private final FuncionRepository funcionRepository;
    private final TurnoFuncionMapper mapper;

    // ─── LISTAR ─────────────────────────────────────────

    public List<TurnoFuncionResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER POR ID ─────────────────────────────────

    public TurnoFuncionResponse obtenerPorId(Long id) {
        TurnoFuncion turno = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TurnoFuncion", "id", id));

        return mapper.toResponse(turno);
    }

    // ─── CREAR ──────────────────────────────────────────

    public TurnoFuncionResponse guardar(TurnoFuncionRequest request) {

        Funcion funcion = funcionRepository.findById(request.getFuncionId())
                .orElseThrow(() -> new EntityNotFoundException("Funcion", "id", request.getFuncionId()));

        TurnoFuncion turno = mapper.toEntity(request);
        turno.setFuncion(funcion);

        return mapper.toResponse(repository.save(turno));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        TurnoFuncion turno = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TurnoFuncion", "id", id));

        repository.delete(turno);
    }

    // ─── POR FUNCION ────────────────────────────────────

    public List<TurnoFuncionResponse> porFuncion(Long funcionId) {

        if (!funcionRepository.existsById(funcionId)) {
            throw new EntityNotFoundException("Funcion", "id", funcionId);
        }

        return repository.findByFuncionId(funcionId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── POR PERSONAL ───────────────────────────────────

    public List<TurnoFuncionResponse> porPersonal(Long personalId) {
        return repository.findByIdPersonalCargo(personalId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
