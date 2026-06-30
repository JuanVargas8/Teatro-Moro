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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        List<MultimediaObraResponse> responses = service.listar();
        responses.forEach(this::addLinks);
        return responses;
    }

    @GetMapping("/{id}")
    public MultimediaObraResponse obtener(@PathVariable Long id) {
        MultimediaObraResponse response = service.obtenerPorId(id);
        return addLinks(response);
    }

    @PostMapping
    public MultimediaObraResponse crear(@RequestBody MultimediaObraRequest request) {
        MultimediaObraResponse response = service.guardar(request);
        return addLinks(response);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTROS ─────────────────────────────────────

    @GetMapping("/obra/{obraId}")
    public List<MultimediaObraResponse> porObra(@PathVariable Long obraId) {
        List<MultimediaObraResponse> responses = service.porObra(obraId);
        responses.forEach(this::addLinks);
        return responses;
    }

    @GetMapping("/tipo")
    public List<MultimediaObraResponse> porTipo(@RequestParam String tipo) {
        List<MultimediaObraResponse> responses = service.porTipo(tipo);
        responses.forEach(this::addLinks);
        return responses;
    }

    private MultimediaObraResponse addLinks(MultimediaObraResponse response) {
        if (response != null) {
            response.add(linkTo(methodOn(MultimediaObraController.class).obtener(response.getId())).withSelfRel());
            response.add(linkTo(methodOn(MultimediaObraController.class).listar()).withRel("all"));
        }
        return response;
    }
}
