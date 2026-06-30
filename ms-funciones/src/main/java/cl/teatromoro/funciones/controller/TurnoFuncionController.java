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

import cl.teatromoro.funciones.dto.TurnoFuncionRequest;
import cl.teatromoro.funciones.dto.TurnoFuncionResponse;
import cl.teatromoro.funciones.service.TurnoFuncionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/turnos")
@RequiredArgsConstructor
@Tag(name = "Controlador de turnos de función", description = "API para gestión de los turnos de las funciones")
public class TurnoFuncionController {

    private final TurnoFuncionService service;

    // ─── LISTAR ─────────────────────────────────────────

    @Operation(summary = "Listar todos los turnos", description = "Retorna una lista de todos los turnos de funciones")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Lista de turnos obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TurnoFuncionResponse.class))))
    @GetMapping
    public List<TurnoFuncionResponse> listar() {
        List<TurnoFuncionResponse> responses = service.listar();
        responses.forEach(this::addLinks);
        return responses;
    }

    // ─── OBTENER POR ID ────────────────────────────────

    @Operation(summary = "Obtener un turno por ID", description = "Retorna los detalles de un turno específico por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Turno obtenido exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TurnoFuncionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado")
    })
    @GetMapping("/{id}")
    public TurnoFuncionResponse obtener(
            @Parameter(description = "ID del turno", required = true, example = "1") @PathVariable Long id) {
        TurnoFuncionResponse response = service.obtenerPorId(id);
        return addLinks(response);
    }

    // ─── CREAR ─────────────────────────────────────────

    @Operation(summary = "Crear un nuevo turno", description = "Crea un nuevo turno de función en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Turno creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TurnoFuncionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public TurnoFuncionResponse crear(
            @Parameter(description = "Detalles del nuevo turno", required = true) @RequestBody TurnoFuncionRequest request) {
        TurnoFuncionResponse response = service.guardar(request);
        return addLinks(response);
    }

    // ─── ELIMINAR ──────────────────────────────────────

    @Operation(summary = "Eliminar un turno", description = "Elimina un turno de función del sistema por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Turno eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID del turno a eliminar", required = true, example = "1") @PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTRO POR FUNCION ───────────────────────────

    @Operation(summary = "Buscar turnos por función", description = "Retorna una lista de turnos filtrados por el ID de la función")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de turnos obtenida exitosamente")
    })
    @GetMapping("/funcion/{funcionId}")
    public List<TurnoFuncionResponse> porFuncion(
            @Parameter(description = "ID de la función", required = true, example = "1") @PathVariable Long funcionId) {
        List<TurnoFuncionResponse> responses = service.porFuncion(funcionId);
        responses.forEach(this::addLinks);
        return responses;
    }

    // ─── FILTRO POR PERSONAL ──────────────────────────

    @Operation(summary = "Buscar turnos por personal", description = "Retorna una lista de turnos filtrados por el ID del personal")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de turnos obtenida exitosamente")
    })
    @GetMapping("/personal/{personalId}")
    public List<TurnoFuncionResponse> porPersonal(
            @Parameter(description = "ID del personal", required = true, example = "1") @PathVariable Long personalId) {
        List<TurnoFuncionResponse> responses = service.porPersonal(personalId);
        responses.forEach(this::addLinks);
        return responses;
    }

    private TurnoFuncionResponse addLinks(TurnoFuncionResponse response) {
        if (response != null) {
            response.add(linkTo(methodOn(TurnoFuncionController.class).obtener(response.getId())).withSelfRel());
            response.add(linkTo(methodOn(TurnoFuncionController.class).listar()).withRel("all"));
        }
        return response;
    }
}
