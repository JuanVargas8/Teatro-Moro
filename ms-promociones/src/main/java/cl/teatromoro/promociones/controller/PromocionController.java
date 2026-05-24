package cl.teatromoro.promociones.controller;

import cl.teatromoro.promociones.model.Campana;
import cl.teatromoro.promociones.model.ProgramaLealtad;
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

    // Endpoints para Cupones
    @GetMapping("/cupones")
    public List<Promocion> getPromos() { 
        return service.listarPromociones(); 
    }
    @PostMapping("/cupones")
    public Promocion savePromo(@RequestBody Promocion p) { 
        return service.guardarPromocion(p); 
    }

    // Endpoints para Campañas
    @GetMapping("/campanas")
    public List<Campana> getCampanas() { 
        return service.listarCampanas(); 
    }
    @PostMapping("/campanas")
    public Campana saveCampana(@RequestBody Campana c) { 
        return service.guardarCampana(c); 
    }

    // Endpoints para Lealtad
    @GetMapping("/lealtad")
    public List<ProgramaLealtad> getLealtad() { 
        return service.listarLealtad(); 
    }
    @PostMapping("/lealtad")
    public ProgramaLealtad saveLealtad(@RequestBody ProgramaLealtad pl) { 
        return service.guardarLealtad(pl); 
    }
}