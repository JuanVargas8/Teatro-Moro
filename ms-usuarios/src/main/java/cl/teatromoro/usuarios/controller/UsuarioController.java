package cl.teatromoro.usuarios.controller;

import cl.teatromoro.usuarios.model.Usuario;
import cl.teatromoro.usuarios.service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import cl.teatromoro.usuarios.dto.UsuarioDTO;
import cl.teatromoro.usuarios.dto.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Controlador de Usuarios", description = "API para la gestión de usuarios del sistema")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos del usuario inválidos")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(
        @Parameter(description = "Datos del nuevo usuario", required = true) @Valid @RequestBody UsuarioDTO dto) {

    return ResponseEntity.ok(service.crear(dto));
}

    @Operation(summary = "Listar todos los usuarios", description = "Retorna una lista de todos los usuarios registrados")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"))
    @GetMapping
    public List<UsuarioResponseDTO> listar() {
    return service.listar();
}

    @Operation(summary = "Obtener un usuario por ID", description = "Retorna los detalles de un usuario específico por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario obtenido exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public UsuarioResponseDTO obtener(
            @Parameter(description = "ID del usuario", required = true, example = "1") @PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @Operation(summary = "Actualizar un usuario", description = "Actualiza los detalles de un usuario existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public Usuario actualizar(
            @Parameter(description = "ID del usuario a actualizar", required = true, example = "1") @PathVariable Long id,
            @Parameter(description = "Nuevos detalles del usuario", required = true) @RequestBody Usuario usuario) {
        return service.actualizarUsuario(id, usuario);
    }

    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario del sistema por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID del usuario a eliminar", required = true, example = "1") @PathVariable Long id) {
        service.eliminarUsuario(id);
    }
}