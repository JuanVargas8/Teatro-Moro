package cl.teatromoro.informes.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import cl.teatromoro.informes.dto.SnapshotVentasRequest;
import cl.teatromoro.informes.dto.SnapshotVentasResponse;
import cl.teatromoro.informes.service.SnapshotVentasService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/snapshot-ventas")
@RequiredArgsConstructor
public class SnapshotVentasController {

    private final SnapshotVentasService service;

    @GetMapping
    public List<SnapshotVentasResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public SnapshotVentasResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public SnapshotVentasResponse crear(@RequestBody SnapshotVentasRequest request) {
        return service.guardar(request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
