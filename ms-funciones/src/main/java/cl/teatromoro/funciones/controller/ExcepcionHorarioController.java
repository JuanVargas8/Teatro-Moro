package cl.teatromoro.funciones.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.teatromoro.funciones.dto.ExcepcionHorarioRequest;
import cl.teatromoro.funciones.dto.ExcepcionHorarioResponse;
import cl.teatromoro.funciones.service.ExcepcionHorarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/excepciones")
@RequiredArgsConstructor
public class ExcepcionHorarioController {

    private final ExcepcionHorarioService service;

    // ─── LISTAR ─────────────────────────────────────────

    @GetMapping
    public List<ExcepcionHorarioResponse> listar() {
        return service.listar();
    }

    // ─── OBTENER POR ID ────────────────────────────────

    @GetMapping("/{id}")
    public ExcepcionHorarioResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // ─── CREAR ─────────────────────────────────────────

    @PostMapping
    public ExcepcionHorarioResponse crear(@RequestBody ExcepcionHorarioRequest request) {
        return service.guardar(request);
    }

    // ─── ELIMINAR ──────────────────────────────────────

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
