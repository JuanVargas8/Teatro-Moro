package cl.teatromoro.suscripciones.controller;

import cl.teatromoro.suscripciones.dto.PlanDTO;
import cl.teatromoro.suscripciones.dto.PlanResponseDTO;
import cl.teatromoro.suscripciones.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@Tag(
        name = "Controlador de Planes",
        description = "API para la gestión de planes de suscripción"
)
@RestController
@RequestMapping("/planes")
public class PlanController {

    private final PlanService service;

    public PlanController(PlanService service) {
        this.service = service;
    }

    @Operation(
            summary = "Crear plan",
            description = "Crea un nuevo plan de suscripción"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Plan creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlanResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<PlanResponseDTO> crear(
            @Parameter(description = "Datos del plan", required = true)
            @Valid @RequestBody PlanDTO dto) {

        log.info("POST /planes - Creando plan {}", dto.getNombre());

        PlanResponseDTO plan = service.crear(dto);

        plan.add(
                linkTo(methodOn(PlanController.class)
                        .obtener(plan.getId()))
                        .withSelfRel()
        );

        plan.add(
                linkTo(methodOn(PlanController.class)
                        .listar())
                        .withRel("listar")
        );

        return ResponseEntity.ok(plan);
    }

    @Operation(
            summary = "Listar planes",
            description = "Obtiene todos los planes registrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public ResponseEntity<List<PlanResponseDTO>> listar() {

        log.info("GET /planes - Listando planes");

        List<PlanResponseDTO> planes = service.listar();

        planes.forEach(plan ->
                plan.add(
                        linkTo(methodOn(PlanController.class)
                                .obtener(plan.getId()))
                                .withSelfRel()
                )
        );

        return ResponseEntity.ok(planes);
    }

    @Operation(
            summary = "Obtener plan por ID",
            description = "Obtiene un plan específico"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Plan encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlanResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Plan no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDTO> obtener(
            @Parameter(description = "ID del plan", required = true, example = "1")
            @PathVariable Long id) {

        log.info("GET /planes/{} - Buscando plan", id);

        PlanResponseDTO plan = service.obtener(id);

        plan.add(
                linkTo(methodOn(PlanController.class)
                        .obtener(id))
                        .withSelfRel()
        );

        plan.add(
                linkTo(methodOn(PlanController.class)
                        .listar())
                        .withRel("listar")
        );

        return ResponseEntity.ok(plan);
    }

    @Operation(
            summary = "Actualizar plan",
            description = "Actualiza la información de un plan"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Plan actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlanResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Plan no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PlanResponseDTO> actualizar(
            @Parameter(description = "ID del plan", required = true, example = "1")
            @PathVariable Long id,

            @Parameter(description = "Datos del plan", required = true)
            @Valid @RequestBody PlanDTO dto) {

        log.info("PUT /planes/{} - Actualizando plan", id);

        PlanResponseDTO plan = service.actualizar(id, dto);

        plan.add(
                linkTo(methodOn(PlanController.class)
                        .obtener(plan.getId()))
                        .withSelfRel()
        );

        plan.add(
                linkTo(methodOn(PlanController.class)
                        .listar())
                        .withRel("listar")
        );

        return ResponseEntity.ok(plan);
    }

    @Operation(
            summary = "Eliminar plan",
            description = "Elimina un plan del sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Plan eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Plan no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del plan", required = true, example = "1")
            @PathVariable Long id) {

        log.info("DELETE /planes/{} - Eliminando plan", id);

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}