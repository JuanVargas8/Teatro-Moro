package cl.teatromoro.notificaciones.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cl.teatromoro.notificaciones.dto.PreferenciaNotificacionRequest;
import cl.teatromoro.notificaciones.dto.PreferenciaNotificacionResponse;
import cl.teatromoro.notificaciones.service.PreferenciaNotificacionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/preferencias")
@RequiredArgsConstructor
public class PreferenciaNotificacionController {

    private final PreferenciaNotificacionService service;

    // ─── LISTAR ─────────────────────────────────────────

    @GetMapping
    public List<PreferenciaNotificacionResponse> listar() {
        return service.listar();
    }

    // ─── OBTENER POR ID ────────────────────────────────

    @GetMapping("/{idUsuario}")
    public PreferenciaNotificacionResponse obtener(@PathVariable Integer idUsuario) {
        return service.obtenerPorId(idUsuario);
    }

    // ─── CREAR ─────────────────────────────────────────

    @PostMapping
    public PreferenciaNotificacionResponse crear(@RequestBody PreferenciaNotificacionRequest request) {
        return service.guardar(request);
    }

    // ─── ELIMINAR ──────────────────────────────────────

    @DeleteMapping("/{idUsuario}")
    public void eliminar(@PathVariable Integer idUsuario) {
        service.eliminar(idUsuario);
    }

    // ─── FILTRO POR EMAIL ─────────────────────────────

    @GetMapping("/email/{email}")
    public List<PreferenciaNotificacionResponse> porEmail(@PathVariable Boolean email) {
        return service.porEmail(email);
    }

    // ─── FILTRO POR SMS ───────────────────────────────

    @GetMapping("/sms/{sms}")
    public List<PreferenciaNotificacionResponse> porSms(@PathVariable Boolean sms) {
        return service.porSms(sms);
    }
}