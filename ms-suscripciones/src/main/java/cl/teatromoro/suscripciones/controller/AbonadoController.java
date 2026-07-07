package cl.teatromoro.suscripciones.controller;

import cl.teatromoro.suscripciones.dto.AbonadoDTO;
import cl.teatromoro.suscripciones.dto.AbonadoResponseDTO;
import cl.teatromoro.suscripciones.dto.AbonadoUpdateDTO;
import cl.teatromoro.suscripciones.service.AbonadoService;
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
        name = "Controlador de Abonados",
        description = "API para la gestión de abonados y sus suscripciones"
)
@RestController
@RequestMapping("/abonados")
public class AbonadoController {

    private final AbonadoService service;

    public AbonadoController(AbonadoService service) {
        this.service = service;
    }

    @Operation(
            summary = "Crear un abonado",
            description = "Crea un nuevo abonado asociado a un usuario y un plan"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Abonado creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AbonadoResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Usuario o plan no encontrado")
    })
    @PostMapping
    public ResponseEntity<AbonadoResponseDTO> crear(
            @Parameter(description = "Datos del nuevo abonado", required = true)
            @Valid @RequestBody AbonadoDTO dto) {

        log.info("POST /abonados - Creando abonado para usuario {}", dto.getUsuarioId());

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

    @Operation(
            summary = "Listar abonados",
            description = "Obtiene todos los abonados registrados"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado obtenido correctamente"
            )
    })
    @GetMapping
    public List<AbonadoResponseDTO> listar() {

        log.info("GET /abonados - Listando abonados");

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

    @Operation(
            summary = "Obtener abonado por ID",
            description = "Obtiene un abonado específico"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Abonado encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AbonadoResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Abonado no encontrado")
    })
    @GetMapping("/{id}")
    public AbonadoResponseDTO obtener(
            @Parameter(description = "ID del abonado", required = true, example = "1")
            @PathVariable Long id) {

        log.info("GET /abonados/{} - Buscando abonado", id);

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

    @Operation(
            summary = "Actualizar abonado",
            description = "Actualiza el plan y/o la fecha de término de un abonado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Abonado actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AbonadoResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Abonado o plan no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AbonadoResponseDTO> actualizar(
            @Parameter(description = "ID del abonado", required = true, example = "1")
            @PathVariable Long id,

            @Parameter(description = "Datos a actualizar", required = true)
            @Valid @RequestBody AbonadoUpdateDTO dto) {

        log.info("PUT /abonados/{} - Actualizando abonado", id);

        AbonadoResponseDTO abonado = service.actualizar(id, dto);

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

    @Operation(
            summary = "Buscar abonados por usuario",
            description = "Obtiene todos los abonados asociados a un usuario"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Abonados encontrados"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/usuario/{usuarioId}")
    public List<AbonadoResponseDTO> porUsuario(
            @Parameter(description = "ID del usuario", required = true, example = "1")
            @PathVariable Long usuarioId) {

        log.info("GET /abonados/usuario/{} - Buscando abonados del usuario", usuarioId);

        return service.porUsuario(usuarioId);
    }

    @Operation(
            summary = "Eliminar abonado",
            description = "Elimina un abonado del sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Abonado eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Abonado no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID del abonado", required = true, example = "1")
            @PathVariable Long id) {

        log.info("DELETE /abonados/{} - Eliminando abonado", id);

        service.eliminar(id);
    }
}