package cl.teatromoro.promociones.controller;

import cl.teatromoro.promociones.model.Promocion;
import cl.teatromoro.promociones.service.PromocionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/promociones")
@RequiredArgsConstructor
public class PromocionController {


    private final PromocionService service;

    @GetMapping
    public List<Promocion> getPromos() {
        return service.listarTodas(); 
    }

    @PostMapping
    public Promocion savePromo(@RequestBody Promocion p) {
        return service.guardar(p);
    }
}