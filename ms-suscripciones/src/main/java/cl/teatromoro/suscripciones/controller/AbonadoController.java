package cl.teatromoro.suscripciones.controller;

import cl.teatromoro.suscripciones.dto.AbonadoDTO;
import cl.teatromoro.suscripciones.dto.AbonadoResponseDTO;
import cl.teatromoro.suscripciones.service.AbonadoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/abonados")
public class AbonadoController {

    private final AbonadoService service;

    public AbonadoController(AbonadoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AbonadoResponseDTO> crear(
            @Valid @RequestBody AbonadoDTO dto) {

        AbonadoResponseDTO abonado = service.crear(dto);

        abonado.add(
                linkTo(methodOn(AbonadoController.class)
                        .obtener(abonado.getId()))
                        .withSelfRel()
        );

        abonado.add(
                linkTo(AbonadoController.class)
                        .withRel("listar")
        );

        return ResponseEntity.ok(abonado);
    }

    @GetMapping
    public List<AbonadoResponseDTO> listar() {

        List<AbonadoResponseDTO> abonados = service.listar();

        abonados.forEach(abonado ->

                abonado.add(
                        linkTo(methodOn(AbonadoController.class)
                                .obtener(abonado.getId()))
                                .withSelfRel()
                )

        );

        return abonados;
    }

    @GetMapping("/{id}")
    public AbonadoResponseDTO obtener(@PathVariable Long id) {

        AbonadoResponseDTO abonado = service.obtenerPorId(id);

        abonado.add(
                linkTo(methodOn(AbonadoController.class)
                        .obtener(id))
                        .withSelfRel()
        );

        abonado.add(
                linkTo(AbonadoController.class)
                        .withRel("listar")
        );

        return abonado;
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<AbonadoResponseDTO> porUsuario(@PathVariable Long usuarioId) {
        return service.porUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}