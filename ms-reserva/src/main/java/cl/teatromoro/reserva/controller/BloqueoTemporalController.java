package cl.teatromoro.reserva.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cl.teatromoro.reserva.dto.BloqueoTemporalRequest;
import cl.teatromoro.reserva.dto.BloqueoTemporalResponse;
import cl.teatromoro.reserva.service.BloqueoTemporalService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bloqueos")
@RequiredArgsConstructor
public class BloqueoTemporalController {

    private final BloqueoTemporalService service;

    // ─── LISTAR ─────────────────────────────────────────

    @GetMapping
    public List<BloqueoTemporalResponse> listar() {
        return service.listar();
    }

    // ─── OBTENER POR ID ────────────────────────────────

    @GetMapping("/{id}")
    public BloqueoTemporalResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // ─── CREAR ─────────────────────────────────────────

    @PostMapping
    public BloqueoTemporalResponse crear(@RequestBody BloqueoTemporalRequest request) {
        return service.guardar(request);
    }

    // ─── ELIMINAR ──────────────────────────────────────

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTRO POR SESION ────────────────────────────

    @GetMapping("/sesion/{idSesion}")
    public List<BloqueoTemporalResponse> porSesion(@PathVariable String idSesion) {
        return service.porSesion(idSesion);
    }

    // ─── OBTENER EXPIRADOS ─────────────────────────────

    @GetMapping("/expirados")
    public List<BloqueoTemporalResponse> expirados() {
        return service.expirados();
    }
}