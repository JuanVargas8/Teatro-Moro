package cl.teatromoro.catalogo.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ms-gestion")
public interface CategoriaClient {

    @GetMapping("/salas")
    List<Map<String, Object>> getSalas();

}
