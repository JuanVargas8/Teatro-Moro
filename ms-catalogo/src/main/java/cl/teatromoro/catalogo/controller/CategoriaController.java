package cl.teatromoro.catalogo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import cl.teatromoro.catalogo.dto.CategoriaRequest;
import cl.teatromoro.catalogo.dto.CategoriaResponse;
import cl.teatromoro.catalogo.service.CategoriaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService service;

    // ─── CRUD ─────────────────────────────────────────

    @GetMapping
    public List<CategoriaResponse> listar() {
        List<CategoriaResponse> responses = service.listar();
        responses.forEach(this::addLinks);
        return responses;
    }

    @GetMapping("/{id}")
    public CategoriaResponse obtener(@PathVariable Long id) {
        CategoriaResponse response = service.obtenerPorId(id);
        return addLinks(response);
    }

    @PostMapping
    public CategoriaResponse crear(@RequestBody CategoriaRequest request) {
        CategoriaResponse response = service.guardar(request);
        return addLinks(response);
    }

    @PutMapping("/{id}")
    public CategoriaResponse actualizar(@PathVariable Long id, @RequestBody CategoriaRequest request) {
        CategoriaResponse response = service.actualizar(id, request);
        return addLinks(response);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    private CategoriaResponse addLinks(CategoriaResponse response) {
        if (response != null) {
            response.add(linkTo(methodOn(CategoriaController.class).obtener(response.getId())).withSelfRel());
            response.add(linkTo(methodOn(CategoriaController.class).listar()).withRel("all"));
        }
        return response;
    }
}
