package cl.teatromoro.reserva.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cl.teatromoro.reserva.dto.EstadoAsientoRequest;
import cl.teatromoro.reserva.dto.EstadoAsientoResponse;
import cl.teatromoro.reserva.service.EstadoAsientoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estado")
@RequiredArgsConstructor
public class EstadoAsientoController {

    private final EstadoAsientoService service;

    // ─── LISTAR ─────────────────────────────────────────

    @GetMapping
    public List<EstadoAsientoResponse> listar() {
        return service.listar();
    }

    // ─── OBTENER POR ID ────────────────────────────────

    @GetMapping("/{id}")
    public EstadoAsientoResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // ─── CREAR ─────────────────────────────────────────

    @PostMapping
    public EstadoAsientoResponse crear(@RequestBody EstadoAsientoRequest request) {
        return service.guardar(request);
    }

    // ─── ACTUALIZAR ────────────────────────────────────

    @PutMapping("/{id}")
    public EstadoAsientoResponse actualizar(@PathVariable Long id,
                                            @RequestBody EstadoAsientoRequest request) {
        return service.actualizar(id, request);
    }

    // ─── ELIMINAR ──────────────────────────────────────

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTRO POR FUNCION ───────────────────────────

    @GetMapping("/funcion/{idFuncion}")
    public List<EstadoAsientoResponse> porFuncion(@PathVariable Integer idFuncion) {
        return service.porFuncion(idFuncion);
    }

    // ─── FILTRO POR ESTADO ─────────────────────────────

    @GetMapping("/estado/{estado}")
    public List<EstadoAsientoResponse> porEstado(@PathVariable String estado) {
        return service.porEstado(estado);
    }
}