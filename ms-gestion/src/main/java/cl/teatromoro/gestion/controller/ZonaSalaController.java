package cl.teatromoro.gestion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.teatromoro.gestion.dto.ZonaSalaRequest;
import cl.teatromoro.gestion.dto.ZonaSalaResponse;
import cl.teatromoro.gestion.service.ZonaSalaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/zonas")
@RequiredArgsConstructor
public class ZonaSalaController {

    private final ZonaSalaService service;

    @GetMapping
    public List<ZonaSalaResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ZonaSalaResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public ZonaSalaResponse crear(@RequestBody ZonaSalaRequest request) {
        return service.guardar(request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/sala/{salaId}")
    public List<ZonaSalaResponse> porSala(@PathVariable Long salaId) {
        return service.porSala(salaId);
    }
}
