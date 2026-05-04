package cl.teatromoro.pagos.controller;

import cl.teatromoro.pagos.dto.ReembolsoRequest;
import cl.teatromoro.pagos.dto.ReembolsoResponse;
import cl.teatromoro.pagos.mapper.ReembolsoMapper;
import cl.teatromoro.pagos.model.Reembolso;
import cl.teatromoro.pagos.service.ReembolsoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reembolsos")
@RequiredArgsConstructor
public class ReembolsoController {

    private final ReembolsoService service;
    private final ReembolsoMapper mapper;

    @PostMapping
    public ResponseEntity<ReembolsoResponse> solicitar(@Valid @RequestBody ReembolsoRequest request) {
        Reembolso entidad = mapper.requestToModel(request);
        Reembolso guardado = service.guardar(entidad);
        return new ResponseEntity<>(mapper.modelToResponse(guardado), HttpStatus.CREATED);
    }
}