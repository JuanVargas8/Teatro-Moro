package cl.teatromoro.suscripciones.controller;

import cl.teatromoro.suscripciones.model.Abonado;
import cl.teatromoro.suscripciones.service.AbonadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abonados")
public class AbonadoController {

    private final AbonadoService service;

    public AbonadoController(AbonadoService service) {
        this.service = service;
    }

    @PostMapping
    public Abonado crear(@RequestBody Abonado abonado) {
        return service.crear(abonado);
    }

    @GetMapping
    public List<Abonado> listar() {
        return service.listar();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Abonado> porUsuario(@PathVariable Long usuarioId) {
        return service.porUsuario(usuarioId);
    }
}