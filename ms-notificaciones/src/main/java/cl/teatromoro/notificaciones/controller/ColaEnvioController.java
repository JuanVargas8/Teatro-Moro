package cl.teatromoro.notificaciones.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cl.teatromoro.notificaciones.dto.ColaEnvioRequest;
import cl.teatromoro.notificaciones.dto.ColaEnvioResponse;
import cl.teatromoro.notificaciones.service.ColaEnvioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cola")
@RequiredArgsConstructor
public class ColaEnvioController {

    private final ColaEnvioService service;

    // ─── LISTAR ─────────────────────────────────────────

    @GetMapping
    public List<ColaEnvioResponse> listar() {
        return service.listar();
    }

    // ─── OBTENER POR ID ────────────────────────────────

    @GetMapping("/{id}")
    public ColaEnvioResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // ─── CREAR ─────────────────────────────────────────

    @PostMapping
    public ColaEnvioResponse crear(@RequestBody ColaEnvioRequest request) {
        return service.guardar(request);
    }

    // ─── ELIMINAR ──────────────────────────────────────

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTRO POR USUARIO ───────────────────────────

    @GetMapping("/usuario/{idUsuario}")
    public List<ColaEnvioResponse> porUsuario(@PathVariable Integer idUsuario) {
        return service.porUsuario(idUsuario);
    }

    // ─── FILTRO POR ESTADO ────────────────────────────

    @GetMapping("/estado/{estado}")
    public List<ColaEnvioResponse> porEstado(@PathVariable String estado) {
        return service.porEstado(estado);
    }
}