package cl.teatromoro.gestion.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-funciones")
public interface SalaClient {

    @GetMapping("/funciones/sala/{salaId}")
    List<Map<String, Object>> getFuncionesPorSala(@PathVariable Long salaId);

}
