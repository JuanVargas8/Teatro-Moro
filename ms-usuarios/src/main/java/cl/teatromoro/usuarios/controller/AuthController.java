package cl.teatromoro.usuarios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils;

import cl.teatromoro.usuarios.dto.LoginRequest;
import cl.teatromoro.usuarios.dto.LoginResponse;
import cl.teatromoro.usuarios.dto.RegisterRequest;
import cl.teatromoro.usuarios.dto.UsuarioResponse;
import cl.teatromoro.usuarios.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Controlador de Autenticación", description = "API para autenticación y registro de usuarios")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Iniciar sesión", description = "Autentica a un usuario y retorna un token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Parameter(description = "Credenciales del usuario", required = true) @Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Registrar usuario", description = "Registra un nuevo usuario en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> register(
            @Parameter(description = "Detalles del nuevo usuario", required = true) @Valid @RequestBody RegisterRequest request) {
        UsuarioResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Cerrar sesión", description = "Invalida el token actual del usuario y cierra la sesión")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sesión cerrada exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @Parameter(description = "Token Bearer", required = true) @RequestHeader(value = "Authorization", required = false) String bearerToken) {
        
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            authService.logout(token);
        }
        return ResponseEntity.ok("Sesión cerrada exitosamente");
    }

    @Operation(summary = "Validar token", description = "Verifica si un token es válido y no está en la lista negra")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna true si es válido, false si es inválido")
    })
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(
            @Parameter(description = "Token a validar", required = true) @RequestHeader("Authorization") String bearerToken) {
        
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            boolean isBlacklisted = authService.isTokenBlacklisted(token);
            return ResponseEntity.ok(!isBlacklisted);
        }
        return ResponseEntity.ok(false);
    }
}
