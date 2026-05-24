package cl.teatromoro.notificaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.common.event.ColaEnvioCreatedEvent;
import cl.teatromoro.common.event.ColaEnvioDeletedEvent;
import cl.teatromoro.notificaciones.client.UsuarioClient;
import cl.teatromoro.notificaciones.dto.ColaEnvioRequest;
import cl.teatromoro.notificaciones.dto.ColaEnvioResponse;
import cl.teatromoro.notificaciones.event.ColaEnvioEventProducer;
import cl.teatromoro.notificaciones.exception.ResourceNotFoundException;
import cl.teatromoro.notificaciones.mapper.ColaEnvioMapper;
import cl.teatromoro.notificaciones.model.ColaEnvio;
import cl.teatromoro.notificaciones.repository.ColaEnvioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColaEnvioService {

    private final ColaEnvioEventProducer eventProducer;
    private final UsuarioClient usuarioClient;
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

        //validar usuario Feign
        usuarioClient.obtenerUsuario(request.getIdUsuario());

        //mapear
        ColaEnvio cola = mapper.toEntity(request);
        ColaEnvio saved = repository.save(cola);

        //evento
        ColaEnvioCreatedEvent event = new ColaEnvioCreatedEvent();
        event.setId(saved.getId());
        event.setIdUsuario(saved.getIdUsuario());
        event.setEstado(saved.getEstado());
        event.setReintentos(saved.getReintentos());
        event.setPlantillaId(saved.getPlantilla().getId());
        event.setPlantillaTipo(saved.getPlantilla().getTipo());

        //enviar a Kafka
        eventProducer.sendCreated(event);

        //respuesta
        return mapper.toResponse(saved);
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {

        //buscar
        ColaEnvio cola = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ColaEnvio", id));

        //eliminar
        repository.delete(cola);
        //crear evento
        ColaEnvioDeletedEvent event = new ColaEnvioDeletedEvent();
        event.setId(id);
        //enviar evento
        eventProducer.sendDeleted(event);
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