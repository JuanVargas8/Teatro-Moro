package cl.teatromoro.funciones.controller;

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

import cl.teatromoro.funciones.dto.ExcepcionHorarioRequest;
import cl.teatromoro.funciones.dto.ExcepcionHorarioResponse;
import cl.teatromoro.funciones.service.ExcepcionHorarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/excepciones")
@RequiredArgsConstructor
@Tag(name = "Controlador de excepciones de horario", description = "API para gestión de excepciones de horarios de funciones")
public class ExcepcionHorarioController {

    private final ExcepcionHorarioService service;

    // ─── LISTAR ─────────────────────────────────────────

    @Operation(summary = "Listar todas las excepciones", description = "Retorna una lista de todas las excepciones de horario")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Lista de excepciones obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExcepcionHorarioResponse.class))))
    @GetMapping
    public List<ExcepcionHorarioResponse> listar() {
        List<ExcepcionHorarioResponse> responses = service.listar();
        responses.forEach(this::addLinks);
        return responses;
    }

    // ─── OBTENER POR ID ────────────────────────────────

    @Operation(summary = "Obtener una excepción por ID", description = "Retorna los detalles de una excepción de horario específica por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Excepción obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExcepcionHorarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Excepción no encontrada")
    })
    @GetMapping("/{id}")
    public ExcepcionHorarioResponse obtener(
            @Parameter(description = "ID de la excepción", required = true, example = "1") @PathVariable Long id) {
        ExcepcionHorarioResponse response = service.obtenerPorId(id);
        return addLinks(response);
    }

    // ─── CREAR ─────────────────────────────────────────

    @Operation(summary = "Crear una nueva excepción", description = "Crea una nueva excepción de horario en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Excepción creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExcepcionHorarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ExcepcionHorarioResponse crear(
            @Parameter(description = "Detalles de la nueva excepción", required = true) @RequestBody ExcepcionHorarioRequest request) {
        ExcepcionHorarioResponse response = service.guardar(request);
        return addLinks(response);
    }

    // ─── ELIMINAR ──────────────────────────────────────

    @Operation(summary = "Eliminar una excepción", description = "Elimina una excepción de horario del sistema por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Excepción eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Excepción no encontrada")
    })
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID de la excepción a eliminar", required = true, example = "1") @PathVariable Long id) {
        service.eliminar(id);
    }

    private ExcepcionHorarioResponse addLinks(ExcepcionHorarioResponse response) {
        if (response != null) {
            response.add(linkTo(methodOn(ExcepcionHorarioController.class).obtener(response.getId())).withSelfRel());
            response.add(linkTo(methodOn(ExcepcionHorarioController.class).listar()).withRel("all"));
        }
        return response;
    }
}
