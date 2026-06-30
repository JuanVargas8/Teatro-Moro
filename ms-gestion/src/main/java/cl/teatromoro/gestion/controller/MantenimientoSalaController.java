package cl.teatromoro.gestion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        List<MantenimientoSalaResponse> responses = service.listar();
        responses.forEach(this::addLinks);
        return responses;
    }

    @GetMapping("/{id}")
    public MantenimientoSalaResponse obtener(@PathVariable Long id) {
        MantenimientoSalaResponse response = service.obtenerPorId(id);
        return addLinks(response);
    }

    @PostMapping
    public MantenimientoSalaResponse crear(@RequestBody MantenimientoSalaRequest request) {
        MantenimientoSalaResponse response = service.guardar(request);
        return addLinks(response);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/sala/{salaId}")
    public List<MantenimientoSalaResponse> porSala(@PathVariable Long salaId) {
        List<MantenimientoSalaResponse> responses = service.porSala(salaId);
        responses.forEach(this::addLinks);
        return responses;
    }

    private MantenimientoSalaResponse addLinks(MantenimientoSalaResponse response) {
        if (response != null) {
            response.add(linkTo(methodOn(MantenimientoSalaController.class).obtener(response.getId())).withSelfRel());
            response.add(linkTo(methodOn(MantenimientoSalaController.class).listar()).withRel("all"));
        }
        return response;
    }
}
