package cl.teatromoro.usuarios.service;

import cl.teatromoro.common.security.JwtTokenProvider;
import cl.teatromoro.usuarios.dto.LoginRequest;
import cl.teatromoro.usuarios.dto.LoginResponse;
import cl.teatromoro.usuarios.dto.RegisterRequest;
import cl.teatromoro.usuarios.dto.UsuarioResponse;
import cl.teatromoro.usuarios.model.BlacklistedToken;
import cl.teatromoro.usuarios.model.Usuario;
import cl.teatromoro.usuarios.repository.BlacklistedTokenRepository;
import cl.teatromoro.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BlacklistedTokenRepository blacklistedTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtTokenProvider.generarToken(usuario.getEmail(), usuario.getRol(), usuario.getNombre());
        return new LoginResponse(token);
    }

    public UsuarioResponse register(RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setNombre(request.getNombre());
        usuario.setRol("Cliente"); // Rol por defecto
        usuario.setFechaRegistro(LocalDate.now());

        usuario = usuarioRepository.save(usuario);

        return mapToResponse(usuario);
    }

    public void logout(String token) {
        if (token != null) {
            // Guardamos el token en la lista negra. En un entorno real se extraería 
            // la fecha de expiración del token para que un job limpie la tabla.
            BlacklistedToken blacklistedToken = new BlacklistedToken(token, new Date(System.currentTimeMillis() + 86400000));
            blacklistedTokenRepository.save(blacklistedToken);
        }
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokenRepository.findByToken(token).isPresent();
    }

    private UsuarioResponse mapToResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setEmail(usuario.getEmail());
        response.setNombre(usuario.getNombre());
        response.setRol(usuario.getRol());
        response.setFechaRegistro(usuario.getFechaRegistro());
        return response;
    }
}
