package cl.teatromoro.suscripciones.controller;

import cl.teatromoro.suscripciones.dto.PlanDTO;
import cl.teatromoro.suscripciones.dto.PlanResponseDTO;
import cl.teatromoro.suscripciones.service.PlanService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planes")
public class PlanController {

    private final PlanService service;

    public PlanController(PlanService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PlanResponseDTO> crear(
            @Valid @RequestBody PlanDTO dto) {

        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<PlanResponseDTO>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDTO> obtener(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.obtener(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PlanDTO dto) {

        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}