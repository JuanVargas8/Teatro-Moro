package cl.teatromoro.reserva.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-funciones")
public interface FuncionesClient {

    @GetMapping("/funciones/{id}")
    Object obtenerFuncion(@PathVariable("id") Integer id);
}
