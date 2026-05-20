package cl.teatromoro.catalogo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.teatromoro.catalogo.dto.MultimediaObraRequest;
import cl.teatromoro.catalogo.dto.MultimediaObraResponse;
import cl.teatromoro.catalogo.service.MultimediaObraService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/multimedia")
@RequiredArgsConstructor
public class MultimediaObraController {

    final MultimediaObraService service;

    // ─── CRUD ─────────────────────────────────────────

    @GetMapping
    public List<MultimediaObraResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public MultimediaObraResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public MultimediaObraResponse crear(@RequestBody MultimediaObraRequest request) {
        return service.guardar(request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTROS ─────────────────────────────────────

    @GetMapping("/obra/{obraId}")
    public List<MultimediaObraResponse> porObra(@PathVariable Long obraId) {
        return service.porObra(obraId);
    }

    @GetMapping("/tipo")
    public List<MultimediaObraResponse> porTipo(@RequestParam String tipo) {
        return service.porTipo(tipo);
    }
}
