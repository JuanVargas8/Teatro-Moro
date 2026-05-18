package cl.teatromoro.suscripciones.controller;

import cl.teatromoro.suscripciones.model.Abonado;
import cl.teatromoro.suscripciones.service.AbonadoService;
import org.springframework.web.bind.annotation.*;

import cl.teatromoro.suscripciones.dto.AbonadoDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import cl.teatromoro.suscripciones.dto.AbonadoResponseDTO;
import cl.teatromoro.suscripciones.dto.PlanResponseDTO;

import java.util.List;

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

        return ResponseEntity.ok(service.crear(dto));
}

    @GetMapping
    public List<AbonadoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<AbonadoResponseDTO> porUsuario(@PathVariable Long usuarioId) {
        return service.porUsuario(usuarioId);
    }
}