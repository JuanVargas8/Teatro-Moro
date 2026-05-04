package cl.teatromoro.informes.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import cl.teatromoro.informes.dto.MetricasOcupacionRequest;
import cl.teatromoro.informes.dto.MetricasOcupacionResponse;
import cl.teatromoro.informes.service.MetricasOcupacionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/metricas-ocupacion")
@RequiredArgsConstructor
public class MetricasOcupacionController {

    private final MetricasOcupacionService service;

    @GetMapping
    public List<MetricasOcupacionResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public MetricasOcupacionResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public MetricasOcupacionResponse crear(@RequestBody MetricasOcupacionRequest request) {
        return service.guardar(request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
