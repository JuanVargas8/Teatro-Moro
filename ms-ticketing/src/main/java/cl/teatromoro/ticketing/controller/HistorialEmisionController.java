package cl.teatromoro.ticketing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cl.teatromoro.ticketing.dto.HistorialEmisionRequest;
import cl.teatromoro.ticketing.dto.HistorialEmisionResponse;
import cl.teatromoro.ticketing.service.HistorialEmisionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/historial")
@RequiredArgsConstructor
public class HistorialEmisionController {

    private final HistorialEmisionService service;

    // ─── LISTAR ─────────────────────────────────────────

    @GetMapping
    public List<HistorialEmisionResponse> listar() {
        return service.listar();
    }

    // ─── OBTENER POR ID ────────────────────────────────

    @GetMapping("/{id}")
    public HistorialEmisionResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // ─── CREAR ─────────────────────────────────────────

    @PostMapping
    public HistorialEmisionResponse crear(@RequestBody HistorialEmisionRequest request) {
        return service.guardar(request);
    }

    // ─── ELIMINAR ──────────────────────────────────────

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTRO POR TICKET ────────────────────────────

    @GetMapping("/ticket/{ticketId}")
    public List<HistorialEmisionResponse> porTicket(@PathVariable Long ticketId) {
        return service.porTicket(ticketId);
    }

    // ─── FILTRO POR CANAL ─────────────────────────────

    @GetMapping("/canal/{canal}")
    public List<HistorialEmisionResponse> porCanal(@PathVariable String canal) {
        return service.porCanal(canal);
    }
}