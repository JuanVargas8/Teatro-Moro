package cl.teatromoro.ticketing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cl.teatromoro.ticketing.dto.TipoEntradaRequest;
import cl.teatromoro.ticketing.dto.TipoEntradaResponse;
import cl.teatromoro.ticketing.service.TipoEntradaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tipos")
@RequiredArgsConstructor
public class TipoEntradaController {

    private final TipoEntradaService service;

    // ─── LISTAR ─────────────────────────────────────────

    @GetMapping
    public List<TipoEntradaResponse> listar() {
        return service.listar();
    }

    // ─── OBTENER POR ID ────────────────────────────────

    @GetMapping("/{id}")
    public TipoEntradaResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // ─── CREAR ─────────────────────────────────────────

    @PostMapping
    public TipoEntradaResponse crear(@RequestBody TipoEntradaRequest request) {
        return service.guardar(request);
    }

    // ─── ACTUALIZAR ────────────────────────────────────

    @PutMapping("/{id}")
    public TipoEntradaResponse actualizar(@PathVariable Long id,
                                          @RequestBody TipoEntradaRequest request) {
        return service.actualizar(id, request);
    }

    // ─── ELIMINAR ──────────────────────────────────────

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}