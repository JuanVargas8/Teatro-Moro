package cl.teatromoro.personal.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import cl.teatromoro.personal.dto.ElencosObraRequest;
import cl.teatromoro.personal.dto.ElencosObraResponse;
import cl.teatromoro.personal.service.ElencosObraService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/elencos-obra")
@RequiredArgsConstructor
public class ElencosObraController {

    private final ElencosObraService service;

    @GetMapping
    public List<ElencosObraResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ElencosObraResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public ElencosObraResponse crear(@RequestBody ElencosObraRequest request) {
        return service.guardar(request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/obra/{idObra}")
    public List<ElencosObraResponse> porObra(@PathVariable Long idObra) {
        return service.listar().stream()
                .filter(e -> e.getIdObra().equals(idObra))
                .toList();
    }

    @GetMapping("/personal/{idPersonal}")
    public List<ElencosObraResponse> porPersonal(@PathVariable Long idPersonal) {
        return service.listar().stream()
                .filter(e -> e.getIdPersonal().equals(idPersonal))
                .toList();
    }
}
