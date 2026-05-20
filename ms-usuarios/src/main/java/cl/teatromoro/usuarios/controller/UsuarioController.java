package cl.teatromoro.usuarios.controller;

import cl.teatromoro.usuarios.model.Usuario;
import cl.teatromoro.usuarios.service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import cl.teatromoro.usuarios.dto.UsuarioDTO;
import cl.teatromoro.usuarios.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(
        @Valid @RequestBody UsuarioDTO dto) {

    return ResponseEntity.ok(service.crear(dto));
}

    @GetMapping
    public List<UsuarioResponseDTO> listar() {
    return service.listar();
}

    @GetMapping("/{id}")
    public UsuarioResponseDTO obtener(@PathVariable Long id) {
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