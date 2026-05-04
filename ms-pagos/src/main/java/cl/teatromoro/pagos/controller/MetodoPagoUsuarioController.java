package cl.teatromoro.pagos.controller;

import cl.teatromoro.pagos.dto.MetodoPagoUsuarioRequest;
import cl.teatromoro.pagos.dto.MetodoPagoUsuarioResponse;
import cl.teatromoro.pagos.mapper.MetodoPagoUsuarioMapper;
import cl.teatromoro.pagos.model.MetodoPagoUsuario;
import cl.teatromoro.pagos.service.MetodoPagoUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/metodos-pago")
@RequiredArgsConstructor
public class MetodoPagoUsuarioController {

    private final MetodoPagoUsuarioService service;
    private final MetodoPagoUsuarioMapper mapper;

    @PostMapping
    public ResponseEntity<MetodoPagoUsuarioResponse> crear(@Valid @RequestBody MetodoPagoUsuarioRequest request) {
        MetodoPagoUsuario entidad = mapper.requestToModel(request);
        MetodoPagoUsuario guardado = service.guardar(entidad);
        return new ResponseEntity<>(mapper.modelToResponse(guardado), HttpStatus.CREATED);
    }
}