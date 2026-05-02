package cl.teatromoro.gestion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.teatromoro.gestion.dto.MantenimientoSalaRequest;
import cl.teatromoro.gestion.dto.MantenimientoSalaResponse;
import cl.teatromoro.gestion.service.MantenimientoSalaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mantenimientos")
@RequiredArgsConstructor
public class MantenimientoSalaController {

    private final MantenimientoSalaService service;

    @GetMapping
    public List<MantenimientoSalaResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public MantenimientoSalaResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public MantenimientoSalaResponse crear(@RequestBody MantenimientoSalaRequest request) {
        return service.guardar(request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/sala/{salaId}")
    public List<MantenimientoSalaResponse> porSala(@PathVariable Long salaId) {
        return service.porSala(salaId);
    }
}
