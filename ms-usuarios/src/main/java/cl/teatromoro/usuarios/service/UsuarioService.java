package cl.teatromoro.usuarios.service;

import cl.teatromoro.usuarios.model.Usuario;
import cl.teatromoro.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import cl.teatromoro.usuarios.dto.UsuarioDTO;
import cl.teatromoro.usuarios.dto.UsuarioResponseDTO;
import cl.teatromoro.usuarios.exception.ResourceNotFoundException;
import cl.teatromoro.usuarios.kafka.KafkaProducerService;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    private final KafkaProducerService producer;

    

    public UsuarioService(UsuarioRepository repository, KafkaProducerService producer) {
        this.repository = repository;
        this.producer = producer;
    }

    public UsuarioResponseDTO crearUsuario(Usuario usuario) {

    usuario.setFechaRegistro(LocalDate.now());

    Usuario guardado = repository.save(usuario);

    producer.enviarMensaje(
        "Nuevo usuario registrado: "
                + guardado.getNombre()
    );

    return new UsuarioResponseDTO(
            guardado.getId(),
            guardado.getEmail(),
            guardado.getNombre(),
            guardado.getFechaRegistro()
    );
}

    public UsuarioResponseDTO crear(UsuarioDTO dto) {

    Usuario usuario = new Usuario();

    usuario.setNombre(dto.getNombre());
    usuario.setEmail(dto.getEmail());
    usuario.setPassword(dto.getPassword());

    usuario.setFechaRegistro(LocalDate.now());

    Usuario guardado = repository.save(usuario);

    return new UsuarioResponseDTO(
            guardado.getId(),
            guardado.getEmail(),
            guardado.getNombre(),
            guardado.getFechaRegistro()
    );
}

    public List<UsuarioResponseDTO> listar() {

    return repository.findAll()
            .stream()
            .map(usuario -> new UsuarioResponseDTO(
                    usuario.getId(),
                    usuario.getEmail(),
                    usuario.getNombre(),
                    usuario.getFechaRegistro()
            ))
            .toList();
}

    public UsuarioResponseDTO obtenerPorId(Long id) {

    Usuario usuario = repository.findById(id)
            .orElseThrow(() ->
              new ResourceNotFoundException("Usuario no encontrado"));

    return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getEmail(),
            usuario.getNombre(),
            usuario.getFechaRegistro()
    );
}

    private Usuario buscarEntidadPorId(Long id) {

    return repository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Usuario no encontrado"));
}

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario existente = buscarEntidadPorId(id);

        existente.setNombre(usuario.getNombre());
        existente.setEmail(usuario.getEmail());

        return repository.save(existente);
    }

    public void eliminarUsuario(Long id) {
        repository.deleteById(id);
    }
}