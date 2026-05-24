package cl.teatromoro.promociones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "ms-pagos")
public interface PagosClient {

    @GetMapping("/api/transacciones/{id}")
    Object obtenerTransaccionPorId(@PathVariable("id") Long id);
}