package cl.teatromoro.notificaciones.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cl.teatromoro.notificaciones.dto.PlantillaNotificacionRequest;
import cl.teatromoro.notificaciones.dto.PlantillaNotificacionResponse;
import cl.teatromoro.notificaciones.service.PlantillaNotificacionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/plantillas")
@RequiredArgsConstructor
public class PlantillaNotificacionController {

    private final PlantillaNotificacionService service;

    // ─── LISTAR ─────────────────────────────────────────

    @GetMapping
    public List<PlantillaNotificacionResponse> listar() {
        return service.listar();
    }

    // ─── OBTENER POR ID ────────────────────────────────

    @GetMapping("/{id}")
    public PlantillaNotificacionResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // ─── CREAR ─────────────────────────────────────────

    @PostMapping
    public PlantillaNotificacionResponse crear(@RequestBody PlantillaNotificacionRequest request) {
        return service.guardar(request);
    }

    // ─── ELIMINAR ──────────────────────────────────────

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTRO POR TIPO ───────────────────────────────

    @GetMapping("/tipo/{tipo}")
    public List<PlantillaNotificacionResponse> porTipo(@PathVariable String tipo) {
        return service.porTipo(tipo);
    }
}