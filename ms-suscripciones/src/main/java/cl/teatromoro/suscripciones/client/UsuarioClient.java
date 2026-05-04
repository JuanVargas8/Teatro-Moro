package cl.teatromoro.suscripciones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-usuarios")
public interface UsuarioClient {

    @GetMapping("/usuarios/{id}")
    Object obtenerUsuario(@PathVariable("id") Long id);
}