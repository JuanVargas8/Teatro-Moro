package cl.teatromoro.funciones.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-catalogo")
public interface FuncionClient {

    @GetMapping("/obras")
    List<Map<String, Object>> getObras();

    @GetMapping("/obras/{id}")
    Map<String, Object> getObraById(@PathVariable Long id);

    @GetMapping("/obras/categoria/{categoriaId}")
    List<Map<String, Object>> getObrasPorCategoria(@PathVariable Long categoriaId);

    @GetMapping("/obras/buscar")
    List<Map<String, Object>> buscarObrasPorTitulo(@RequestParam String titulo);

}
