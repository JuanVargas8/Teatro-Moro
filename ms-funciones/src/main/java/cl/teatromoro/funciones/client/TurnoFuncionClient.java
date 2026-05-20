package cl.teatromoro.funciones.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-gestion")
public interface TurnoFuncionClient {

    @GetMapping("/salas")
    List<Map<String, Object>> getSalas();

    @GetMapping("/salas/{id}")
    Map<String, Object> getSalaById(@PathVariable Long id);

}
