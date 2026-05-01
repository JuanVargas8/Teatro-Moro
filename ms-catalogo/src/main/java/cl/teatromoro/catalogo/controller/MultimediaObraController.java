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

import cl.teatromoro.catalogo.entity.MultimediaObra;
import cl.teatromoro.catalogo.service.MultimediaObraService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/multimedia")
@RequiredArgsConstructor
public class MultimediaObraController {

    private final MultimediaObraService service;

    @GetMapping
    public List<MultimediaObra> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public MultimediaObra obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public MultimediaObra crear(@RequestBody MultimediaObra multimedia) {
        return service.guardar(multimedia);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // 🔥 ENDPOINTS ÚTILES

    @GetMapping("/obra/{obraId}")
    public List<MultimediaObra> porObra(@PathVariable Long obraId) {
        return service.porObra(obraId);
    }

    @GetMapping("/tipo")
    public List<MultimediaObra> porTipo(@RequestParam String tipo) {
        return service.porTipo(tipo);
    }
}
