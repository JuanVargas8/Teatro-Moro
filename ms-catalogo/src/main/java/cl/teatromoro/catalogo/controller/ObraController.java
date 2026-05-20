package cl.teatromoro.catalogo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.teatromoro.catalogo.dto.ObraRequest;
import cl.teatromoro.catalogo.dto.ObraResponse;
import cl.teatromoro.catalogo.service.ObraService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class ObraController {

    private final ObraService service;

    // ─── CRUD ─────────────────────────────────────────

    @GetMapping
    public List<ObraResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ObraResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public ObraResponse crear(@RequestBody ObraRequest request) {
        return service.guardar(request);
    }

    @PutMapping("/{id}")
    public ObraResponse actualizar(@PathVariable Long id, @RequestBody ObraRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTROS ─────────────────────────────────────

    @GetMapping("/buscar")
    public List<ObraResponse> buscarPorTitulo(@RequestParam String titulo) {
        return service.buscarPorTitulo(titulo);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<ObraResponse> porCategoria(@PathVariable Long categoriaId) {
        return service.porCategoria(categoriaId);
    }

    @GetMapping("/largas")
    public List<ObraResponse> largas(@RequestParam int minutos) {
        return service.largas(minutos);
    }
}
