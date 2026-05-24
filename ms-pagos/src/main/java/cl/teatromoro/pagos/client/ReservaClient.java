package cl.teatromoro.pagos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El name "ms-reserva" es el nombre con el que tu compañero registró el MS en Eureka
@FeignClient(name = "ms-reserva")
public interface ReservaClient {

    // ─── CONEXIÓN A BloqueoTemporalController ───────────────────
    // Llama al endpoint: GET /bloqueos/{id}
    @GetMapping("/bloqueos/{id}")
    Object obtenerBloqueoPorId(@PathVariable("id") Long id);

    // ─── CONEXIÓN A EstadoAsientoController ─────────────────────
    // Llama al endpoint: GET /estado/{id}
    @GetMapping("/estado/{id}")
    Object obtenerEstadoAsientoPorId(@PathVariable("id") Long id);
}