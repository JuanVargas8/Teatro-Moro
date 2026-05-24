package cl.teatromoro.promociones.controller;

import cl.teatromoro.promociones.dto.CampanaRequest;
import cl.teatromoro.promociones.dto.CampanaResponse;
import cl.teatromoro.promociones.mapper.CampanaMapper;
import cl.teatromoro.promociones.model.Campana;
import cl.teatromoro.promociones.service.CampanaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/campanas")
@RequiredArgsConstructor
public class CampanaController {

    private final CampanaService service;
    private final CampanaMapper mapper;

    @PostMapping
    public ResponseEntity<CampanaResponse> crear(@Valid @RequestBody CampanaRequest request) {
        Campana entidad = mapper.requestToModel(request);
        Campana guardada = service.guardar(entidad);
        return new ResponseEntity<>(mapper.modelToResponse(guardada), HttpStatus.CREATED);
    }
}