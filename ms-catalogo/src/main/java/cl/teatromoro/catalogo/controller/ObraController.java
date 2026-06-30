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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        List<ObraResponse> responses = service.listar();
        responses.forEach(this::addLinks);
        return responses;
    }

    @GetMapping("/{id}")
    public ObraResponse obtener(@PathVariable Long id) {
        ObraResponse response = service.obtenerPorId(id);
        return addLinks(response);
    }

    @PostMapping
    public ObraResponse crear(@RequestBody ObraRequest request) {
        ObraResponse response = service.guardar(request);
        return addLinks(response);
    }

    @PutMapping("/{id}")
    public ObraResponse actualizar(@PathVariable Long id, @RequestBody ObraRequest request) {
        ObraResponse response = service.actualizar(id, request);
        return addLinks(response);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTROS ─────────────────────────────────────

    @GetMapping("/buscar")
    public List<ObraResponse> buscarPorTitulo(@RequestParam String titulo) {
        List<ObraResponse> responses = service.buscarPorTitulo(titulo);
        responses.forEach(this::addLinks);
        return responses;
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<ObraResponse> porCategoria(@PathVariable Long categoriaId) {
        List<ObraResponse> responses = service.porCategoria(categoriaId);
        responses.forEach(this::addLinks);
        return responses;
    }

    @GetMapping("/largas")
    public List<ObraResponse> largas(@RequestParam int minutos) {
        List<ObraResponse> responses = service.largas(minutos);
        responses.forEach(this::addLinks);
        return responses;
    }

    private ObraResponse addLinks(ObraResponse response) {
        if (response != null) {
            response.add(linkTo(methodOn(ObraController.class).obtener(response.getId())).withSelfRel());
            response.add(linkTo(methodOn(ObraController.class).listar()).withRel("all"));
        }
        return response;
    }
}
