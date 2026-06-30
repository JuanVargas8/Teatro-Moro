package cl.teatromoro.funciones.controller;

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

import cl.teatromoro.funciones.dto.FuncionRequest;
import cl.teatromoro.funciones.dto.FuncionResponse;
import cl.teatromoro.funciones.service.FuncionService;
import io.micrometer.common.lang.NonNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/funciones")
@RequiredArgsConstructor
@Tag(name = "Controlador de funciones", description = "API para gestión de funciones")
public class FuncionController {

    private final FuncionService service;

    // ─── CRUD ─────────────────────────────────────────
    @Operation(summary = "Listar todas las funciones", description = "Retorna una lista de todas las funciones")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Lista de funciones obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionResponse.class))))
    @GetMapping
    public List<FuncionResponse> listar() {
        List<FuncionResponse> responses = service.listar();
        responses.forEach(this::addLinks);
        return responses;
    }

    @Operation(summary = "Obtener una función por ID", description = "Retorna los detalles de una función específica por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Función obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Función no encontrada")
    })
    @GetMapping("/{id}")
    public FuncionResponse obtener(
            @Parameter(description = "ID de la función", required = true, example = "1") @PathVariable @NonNull Long id) {

        FuncionResponse response = service.obtenerPorId(id);
        return addLinks(response);
    }

    @Operation(summary = "Crear una nueva función", description = "Crea una nueva función en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Función creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public FuncionResponse crear(
            @Parameter(description = "Detalles de la nueva función", required = true) @RequestBody FuncionRequest request) {
        FuncionResponse response = service.guardar(request);
        return addLinks(response);
    }

    @Operation(summary = "Actualizar una función existente", description = "Actualiza los detalles de una función existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Función actualizada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuncionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Función no encontrada")
    })
    @PutMapping("/{id}")
    public FuncionResponse actualizar(
            @Parameter(description = "ID de la función a actualizar", required = true, example = "1") @PathVariable Long id,
            @Parameter(description = "Nuevos detalles de la función", required = true) @RequestBody FuncionRequest request) {
        FuncionResponse response = service.actualizar(id, request);
        return addLinks(response);
    }

    @Operation(summary = "Eliminar una función", description = "Elimina una función del sistema por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Función eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Función no encontrada")
    })
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID de la función a eliminar", required = true, example = "1") @PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTROS ─────────────────────────────────────

    @Operation(summary = "Buscar funciones por obra", description = "Retorna una lista de funciones filtradas por el ID de la obra")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de funciones obtenida exitosamente")
    })
    @GetMapping("/obra/{obraId}")
    public List<FuncionResponse> porObra(
            @Parameter(description = "ID de la obra", required = true, example = "1") @PathVariable Long obraId) {
        List<FuncionResponse> responses = service.porObra(obraId);
        responses.forEach(this::addLinks);
        return responses;
    }

    @Operation(summary = "Buscar funciones por sala", description = "Retorna una lista de funciones filtradas por el ID de la sala")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de funciones obtenida exitosamente")
    })
    @GetMapping("/sala/{salaId}")
    public List<FuncionResponse> porSala(
            @Parameter(description = "ID de la sala", required = true, example = "1") @PathVariable Long salaId) {
        List<FuncionResponse> responses = service.porSala(salaId);
        responses.forEach(this::addLinks);
        return responses;
    }

    private FuncionResponse addLinks(FuncionResponse response) {
        if (response != null) {
            response.add(linkTo(methodOn(FuncionController.class).obtener(response.getId())).withSelfRel());
            response.add(linkTo(methodOn(FuncionController.class).listar()).withRel("all"));
        }
        return response;
    }
}
