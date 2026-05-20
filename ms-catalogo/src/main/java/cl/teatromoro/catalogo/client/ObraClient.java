package cl.teatromoro.catalogo.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-funciones")
public interface ObraClient {

    @GetMapping("/funciones/obra/{obraId}")
    List<Map<String, Object>> getFuncionesPorObra(@PathVariable Long obraId);

}
