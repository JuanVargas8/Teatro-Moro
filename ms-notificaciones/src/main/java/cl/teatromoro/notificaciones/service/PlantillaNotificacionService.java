package cl.teatromoro.notificaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.notificaciones.dto.PlantillaNotificacionRequest;
import cl.teatromoro.notificaciones.dto.PlantillaNotificacionResponse;
import cl.teatromoro.notificaciones.exception.ResourceNotFoundException;
import cl.teatromoro.notificaciones.mapper.PlantillaNotificacionMapper;
import cl.teatromoro.notificaciones.model.PlantillaNotificacion;
import cl.teatromoro.notificaciones.repository.PlantillaNotificacionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlantillaNotificacionService {

    private final PlantillaNotificacionRepository repository;
    private final PlantillaNotificacionMapper mapper;

    // ─── LISTAR ─────────────────────────────────────────

    public List<PlantillaNotificacionResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER ────────────────────────────────────────

    public PlantillaNotificacionResponse obtenerPorId(Long id) {
        PlantillaNotificacion plantilla = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PlantillaNotificacion", id));

        return mapper.toResponse(plantilla);
    }

    // ─── CREAR ──────────────────────────────────────────

    public PlantillaNotificacionResponse guardar(PlantillaNotificacionRequest request) {
        PlantillaNotificacion plantilla = mapper.toEntity(request);
        return mapper.toResponse(repository.save(plantilla));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        PlantillaNotificacion plantilla = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PlantillaNotificacion", id));

        repository.delete(plantilla);
    }

    // ─── FILTRO POR TIPO ───────────────────────────────

    public List<PlantillaNotificacionResponse> porTipo(String tipo) {
        return repository.findByTipoContaining(tipo)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}