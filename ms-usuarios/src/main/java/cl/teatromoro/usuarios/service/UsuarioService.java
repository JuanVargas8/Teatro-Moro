package cl.teatromoro.usuarios.service;

import cl.teatromoro.usuarios.model.Usuario;
import cl.teatromoro.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        usuario.setFechaRegistro(LocalDate.now());
        return repository.save(usuario);
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

        return repository.save(existente);
    }

    public void eliminarUsuario(Long id) {
        repository.deleteById(id);
    }
}