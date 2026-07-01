package cl.teatromoro.common.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;

/**
 * Filtro de autenticación JWT que se ejecuta UNA VEZ por cada petición HTTP.
 *
 * Flujo:
 * 1. Lee el header "Authorization: Bearer <token>", la palabra Bearer significa portador.
 * 2. Valida el token con JwtTokenProvider
 * 3. Extrae email y rol del token
 * 4. Crea un objeto Authentication y lo inyecta en el SecurityContext
 * 5. Spring Security usa ese contexto para autorizar el acceso al endpoint
 *
 * Si el token no existe o es inválido, el filtro simplemente NO autentica
 * y deja que Spring Security rechace la petición con 401.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final @Lazy AuthClient authClient;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

    String path = request.getServletPath();

    return path.startsWith("/api/v1/auth/");
}

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            // 1. Extraer el token del header Authorization
            String token = extraerToken(request);

            // 2. Validar y autenticar solo si existe un token
            if (StringUtils.hasText(token) && jwtTokenProvider.validarToken(token)) {
                
                // Verificar en la lista negra llamando a ms-usuarios
                try {
                    ResponseEntity<Boolean> isValidResponse = authClient.validateToken("Bearer " + token);
                    if (isValidResponse.getBody() != null && !isValidResponse.getBody()) {
                        log.warn("Token JWT está en la lista negra");
                        SecurityContextHolder.clearContext();
                        filterChain.doFilter(request, response);
                        return;
                    }
                } catch (Exception ex) {
                    log.error("Error al verificar lista negra de tokens: {}", ex.getMessage());
                    // Si falla la verificación, por seguridad podríamos rechazar, pero para 
                    // no romper el sistema si ms-usuarios está caído, permitimos continuar
                    // o lo bloqueamos. Asumiremos que debe estar disponible.
                }

                // 3. Extraer claims del token
                String email = jwtTokenProvider.getEmailFromToken(token);
                String rol = jwtTokenProvider.getRolFromToken(token);

                // 4. Crear authorities con prefijo ROLE_ (convención de Spring Security)
                List<SimpleGrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_" + rol)
                );

                // 5. Crear objeto de autenticación (pasamos el token original como credenciales para el Interceptor de Feign)
                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(email, token, authorities);

                // 6. Inyectar en el SecurityContext para que Spring Security lo use
                SecurityContextHolder.getContext().setAuthentication(authentication);

                log.debug("Usuario autenticado: {} con rol: {}", email, rol);
            }

        } catch (Exception e) {
            log.error("Error al procesar el token JWT: {}", e.getMessage());
            // No lanzamos excepción; dejamos que Spring Security maneje el 401
            SecurityContextHolder.clearContext();
        }

        // 7. Continuar con la cadena de filtros (siempre, haya o no token)
        filterChain.doFilter(request, response);
    }

    /**
     * Extrae el token JWT del header Authorization.
     * Espera el formato: "Bearer eyJhbGciOiJIUzI1NiJ9..."
     *
     * @return el token sin el prefijo "Bearer ", o null si no existe
     */
    private String extraerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }
}
