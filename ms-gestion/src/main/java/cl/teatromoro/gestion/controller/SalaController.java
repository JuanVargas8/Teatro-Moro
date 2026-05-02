package cl.teatromoro.gestion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.teatromoro.gestion.dto.SalaRequest;
import cl.teatromoro.gestion.dto.SalaResponse;
import cl.teatromoro.gestion.service.SalaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/salas")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService service;

    @GetMapping
    public List<SalaResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public SalaResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public SalaResponse crear(@RequestBody SalaRequest request) {
        return service.guardar(request);
    }

    @PutMapping("/{id}")
    public SalaResponse actualizar(@PathVariable Long id, @RequestBody SalaRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
