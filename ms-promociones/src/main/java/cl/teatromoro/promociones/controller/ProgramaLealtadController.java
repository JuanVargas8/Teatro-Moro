package cl.teatromoro.promociones.controller;

import cl.teatromoro.promociones.dto.ProgramaLealtadRequest;
import cl.teatromoro.promociones.dto.ProgramaLealtadResponse;
import cl.teatromoro.promociones.mapper.ProgramaLealtadMapper;
import cl.teatromoro.promociones.model.ProgramaLealtad;
import cl.teatromoro.promociones.service.ProgramaLealtadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lealtad")
@RequiredArgsConstructor
public class ProgramaLealtadController {

    private final ProgramaLealtadService service;
    private final ProgramaLealtadMapper mapper;

    @PostMapping
    public ResponseEntity<ProgramaLealtadResponse> crear(@Valid @RequestBody ProgramaLealtadRequest request) {
        ProgramaLealtad entidad = mapper.requestToModel(request);
        ProgramaLealtad guardada = service.guardar(entidad);
        return new ResponseEntity<>(mapper.modelToResponse(guardada), HttpStatus.CREATED);
    }
}