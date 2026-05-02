package cl.teatromoro.funciones.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.teatromoro.funciones.dto.TurnoFuncionRequest;
import cl.teatromoro.funciones.dto.TurnoFuncionResponse;
import cl.teatromoro.funciones.service.TurnoFuncionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/turnos")
@RequiredArgsConstructor
public class TurnoFuncionController {

    private final TurnoFuncionService service;

    // ─── LISTAR ─────────────────────────────────────────

    @GetMapping
    public List<TurnoFuncionResponse> listar() {
        return service.listar();
    }

    // ─── OBTENER POR ID ────────────────────────────────

    @GetMapping("/{id}")
    public TurnoFuncionResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // ─── CREAR ─────────────────────────────────────────

    @PostMapping
    public TurnoFuncionResponse crear(@RequestBody TurnoFuncionRequest request) {
        return service.guardar(request);
    }

    // ─── ELIMINAR ──────────────────────────────────────

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTRO POR FUNCION ───────────────────────────

    @GetMapping("/funcion/{funcionId}")
    public List<TurnoFuncionResponse> porFuncion(@PathVariable Long funcionId) {
        return service.porFuncion(funcionId);
    }

    // ─── FILTRO POR PERSONAL ──────────────────────────

    @GetMapping("/personal/{personalId}")
    public List<TurnoFuncionResponse> porPersonal(@PathVariable Long personalId) {
        return service.porPersonal(personalId);
    }
}
