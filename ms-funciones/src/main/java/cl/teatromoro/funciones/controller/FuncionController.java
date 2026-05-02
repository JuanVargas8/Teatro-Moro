package cl.teatromoro.funciones.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.teatromoro.funciones.dto.FuncionRequest;
import cl.teatromoro.funciones.dto.FuncionResponse;
import cl.teatromoro.funciones.service.FuncionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/funciones")
@RequiredArgsConstructor
public class FuncionController {


    private final FuncionService service;

    // ─── CRUD ─────────────────────────────────────────

    @GetMapping
    public List<FuncionResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public FuncionResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public FuncionResponse crear(@RequestBody FuncionRequest request) {
        return service.guardar(request);
    }

    @PutMapping("/{id}")
    public FuncionResponse actualizar(@PathVariable Long id, @RequestBody FuncionRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTROS ─────────────────────────────────────

    @GetMapping("/obra/{obraId}")
    public List<FuncionResponse> porObra(@PathVariable Long obraId) {
        return service.porObra(obraId);
    }

    @GetMapping("/sala/{salaId}")
    public List<FuncionResponse> porSala(@PathVariable Long salaId) {
        return service.porSala(salaId);
    }
}
