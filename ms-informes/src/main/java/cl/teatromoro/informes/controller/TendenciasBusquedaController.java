package cl.teatromoro.informes.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import cl.teatromoro.informes.dto.TendenciasBusquedaRequest;
import cl.teatromoro.informes.dto.TendenciasBusquedaResponse;
import cl.teatromoro.informes.service.TendenciasBusquedaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tendencias-busqueda")
@RequiredArgsConstructor
public class TendenciasBusquedaController {

    private final TendenciasBusquedaService service;

    @GetMapping
    public List<TendenciasBusquedaResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public TendenciasBusquedaResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public TendenciasBusquedaResponse crear(@RequestBody TendenciasBusquedaRequest request) {
        return service.guardar(request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
