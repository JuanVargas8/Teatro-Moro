package cl.teatromoro.reserva.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.reserva.dto.BloqueoTemporalRequest;
import cl.teatromoro.reserva.dto.BloqueoTemporalResponse;
import cl.teatromoro.reserva.exception.ResourceNotFoundException;
import cl.teatromoro.reserva.mapper.BloqueoTemporalMapper;
import cl.teatromoro.reserva.model.BloqueoTemporal;
import cl.teatromoro.reserva.repository.BloqueoTemporalRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BloqueoTemporalService {

    private final BloqueoTemporalRepository repository;
    private final BloqueoTemporalMapper mapper;

    // ─── LISTAR ─────────────────────────────────────────

    public List<BloqueoTemporalResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER ────────────────────────────────────────

    public BloqueoTemporalResponse obtenerPorId(Long id) {
        BloqueoTemporal entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BloqueoTemporal", id));

        return mapper.toResponse(entity);
    }

    // ─── CREAR ──────────────────────────────────────────

    public BloqueoTemporalResponse guardar(BloqueoTemporalRequest request) {
        BloqueoTemporal entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        BloqueoTemporal entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BloqueoTemporal", id));

        repository.delete(entity);
    }

    // ─── POR SESION ────────────────────────────────────

    public List<BloqueoTemporalResponse> porSesion(String idSesionUsuario) {
        return repository.findByIdSesionUsuario(idSesionUsuario)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── EXPIRADOS ─────────────────────────────────────

    public List<BloqueoTemporalResponse> expirados() {
        return repository.findByExpiracionBefore(LocalDateTime.now())
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}