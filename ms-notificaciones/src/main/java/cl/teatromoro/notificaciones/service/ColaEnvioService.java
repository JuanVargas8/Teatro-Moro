package cl.teatromoro.notificaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.notificaciones.dto.ColaEnvioRequest;
import cl.teatromoro.notificaciones.dto.ColaEnvioResponse;
import cl.teatromoro.notificaciones.exception.ResourceNotFoundException;
import cl.teatromoro.notificaciones.mapper.ColaEnvioMapper;
import cl.teatromoro.notificaciones.model.ColaEnvio;
import cl.teatromoro.notificaciones.repository.ColaEnvioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColaEnvioService {

    private final ColaEnvioRepository repository;
    private final ColaEnvioMapper mapper;

    // ─── LISTAR ─────────────────────────────────────────

    public List<ColaEnvioResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER ────────────────────────────────────────

    public ColaEnvioResponse obtenerPorId(Long id) {
        ColaEnvio cola = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ColaEnvio", id));

        return mapper.toResponse(cola);
    }

    // ─── CREAR ──────────────────────────────────────────

    public ColaEnvioResponse guardar(ColaEnvioRequest request) {
        ColaEnvio cola = mapper.toEntity(request);
        return mapper.toResponse(repository.save(cola));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        ColaEnvio cola = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ColaEnvio", id));

        repository.delete(cola);
    }

    // ─── POR USUARIO ───────────────────────────────────

    public List<ColaEnvioResponse> porUsuario(Integer idUsuario) {
        return repository.findByIdUsuario(idUsuario)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── POR ESTADO ────────────────────────────────────

    public List<ColaEnvioResponse> porEstado(String estado) {
        return repository.findByEstado(estado)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}