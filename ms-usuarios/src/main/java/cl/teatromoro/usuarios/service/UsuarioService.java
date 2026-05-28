package cl.teatromoro.usuarios.service;

import cl.teatromoro.common.event.UsuarioCreatedEvent;
import cl.teatromoro.common.event.UsuarioDeletedEvent;
import cl.teatromoro.common.event.UsuarioUpdatedEvent;
import cl.teatromoro.usuarios.event.UsuarioEventProducer;
import cl.teatromoro.usuarios.model.Usuario;
import cl.teatromoro.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioEventProducer eventProducer;

    public UsuarioService(UsuarioRepository repository, UsuarioEventProducer eventProducer) {
        this.repository = repository;
        this.eventProducer = eventProducer;
    }

    public Usuario crearUsuario(Usuario usuario) {
        usuario.setFechaRegistro(LocalDate.now());
        Usuario guardado = repository.save(usuario);
        
        UsuarioCreatedEvent event = new UsuarioCreatedEvent();
        event.setId(guardado.getId());
        event.setNombre(guardado.getNombre());
        event.setEmail(guardado.getEmail());
        // event.setRol(guardado.getRol()); // If role exists, otherwise leave null or empty
        eventProducer.sendCreated(event);
        
        return guardado;
    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario existente = obtenerPorId(id);

        existente.setNombre(usuario.getNombre());
        existente.setEmail(usuario.getEmail());

        Usuario actualizado = repository.save(existente);
        
        UsuarioUpdatedEvent event = new UsuarioUpdatedEvent();
        event.setId(actualizado.getId());
        event.setNombre(actualizado.getNombre());
        event.setEmail(actualizado.getEmail());
        eventProducer.sendUpdated(event);
        
        return actualizado;
    }

    public void eliminarUsuario(Long id) {
        Usuario existente = obtenerPorId(id);
        repository.deleteById(id);
        
        UsuarioDeletedEvent event = new UsuarioDeletedEvent();
        event.setId(id);
        eventProducer.sendDeleted(event);
    }
}