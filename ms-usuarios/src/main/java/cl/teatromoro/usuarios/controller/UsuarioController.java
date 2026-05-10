package cl.teatromoro.usuarios.controller;

import cl.teatromoro.usuarios.model.Usuario;
import cl.teatromoro.usuarios.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return service.crearUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return service.actualizarUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarUsuario(id);
    }
}