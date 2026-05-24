package cl.teatromoro.notificaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.notificaciones.client.UsuarioClient;
import cl.teatromoro.notificaciones.dto.PreferenciaNotificacionRequest;
import cl.teatromoro.notificaciones.dto.PreferenciaNotificacionResponse;
import cl.teatromoro.notificaciones.exception.ResourceNotFoundException;
import cl.teatromoro.notificaciones.mapper.PreferenciaNotificacionMapper;
import cl.teatromoro.notificaciones.model.PreferenciaNotificacion;
import cl.teatromoro.notificaciones.repository.PreferenciaNotificacionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PreferenciaNotificacionService {

    private final UsuarioClient usuarioClient;
    private final PreferenciaNotificacionRepository repository;
    private final PreferenciaNotificacionMapper mapper;

    // ─── LISTAR ─────────────────────────────────────────

    public List<PreferenciaNotificacionResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER ────────────────────────────────────────

    public PreferenciaNotificacionResponse obtenerPorId(Integer idUsuario) {
        PreferenciaNotificacion entity = repository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("PreferenciaNotificacion", idUsuario));

        return mapper.toResponse(entity);
    }

    // ─── CREAR ──────────────────────────────────────────

    public PreferenciaNotificacionResponse guardar(PreferenciaNotificacionRequest request) {

        System.out.println("ANTES DEL FEIGN");

        usuarioClient.obtenerUsuario(request.getIdUsuario());

        System.out.println("DESPUES DEL FEIGN");

        PreferenciaNotificacion entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Integer idUsuario) {
        PreferenciaNotificacion entity = repository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("PreferenciaNotificacion", idUsuario));

        repository.delete(entity);
    }

    // ─── FILTRO POR EMAIL ──────────────────────────────

    public List<PreferenciaNotificacionResponse> porEmail(Boolean email) {
        return repository.findByEmail(email)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── FILTRO POR SMS ────────────────────────────────

    public List<PreferenciaNotificacionResponse> porSms(Boolean sms) {
        return repository.findBySms(sms)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}