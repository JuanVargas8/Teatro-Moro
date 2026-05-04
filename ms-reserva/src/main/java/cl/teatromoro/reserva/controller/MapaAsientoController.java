package cl.teatromoro.reserva.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cl.teatromoro.reserva.dto.MapaAsientoRequest;
import cl.teatromoro.reserva.dto.MapaAsientoResponse;
import cl.teatromoro.reserva.service.MapaAsientoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/asientos")
@RequiredArgsConstructor
public class MapaAsientoController {

    private final MapaAsientoService service;

    // ─── LISTAR ─────────────────────────────────────────

    @GetMapping
    public List<MapaAsientoResponse> listar() {
        return service.listar();
    }

    // ─── OBTENER POR ID ────────────────────────────────

    @GetMapping("/{id}")
    public MapaAsientoResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // ─── CREAR ─────────────────────────────────────────

    @PostMapping
    public MapaAsientoResponse crear(@RequestBody MapaAsientoRequest request) {
        return service.guardar(request);
    }

    // ─── ACTUALIZAR ────────────────────────────────────

    @PutMapping("/{id}")
    public MapaAsientoResponse actualizar(@PathVariable Long id,
                                          @RequestBody MapaAsientoRequest request) {
        return service.actualizar(id, request);
    }

    // ─── ELIMINAR ──────────────────────────────────────

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTRO POR SALA ───────────────────────────────

    @GetMapping("/sala/{idSala}")
    public List<MapaAsientoResponse> porSala(@PathVariable Integer idSala) {
        return service.porSala(idSala);
    }

    // ─── FILTRO POR FILA ───────────────────────────────

    @GetMapping("/fila/{fila}")
    public List<MapaAsientoResponse> porFila(@PathVariable String fila) {
        return service.porFila(fila);
    }
}