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
        List<ZonaSalaResponse> responses = service.listar();
        responses.forEach(this::addLinks);
        return responses;
    }

    @GetMapping("/{id}")
    public ZonaSalaResponse obtener(@PathVariable Long id) {
        ZonaSalaResponse response = service.obtenerPorId(id);
        return addLinks(response);
    }

    @PostMapping
    public ZonaSalaResponse crear(@RequestBody ZonaSalaRequest request) {
        ZonaSalaResponse response = service.guardar(request);
        return addLinks(response);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/sala/{salaId}")
    public List<ZonaSalaResponse> porSala(@PathVariable Long salaId) {
        List<ZonaSalaResponse> responses = service.porSala(salaId);
        responses.forEach(this::addLinks);
        return responses;
    }

    private ZonaSalaResponse addLinks(ZonaSalaResponse response) {
        if (response != null) {
            response.add(linkTo(methodOn(ZonaSalaController.class).obtener(response.getId())).withSelfRel());
            response.add(linkTo(methodOn(ZonaSalaController.class).listar()).withRel("all"));
        }
        return response;
    }
}
