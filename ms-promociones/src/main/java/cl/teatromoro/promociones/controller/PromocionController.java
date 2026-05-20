package cl.teatromoro.promociones.controller;

import cl.teatromoro.promociones.dto.CampanaRequest;
import cl.teatromoro.promociones.dto.CampanaResponse;
import cl.teatromoro.promociones.model.Campana;
import cl.teatromoro.promociones.model.ProgramaLealtad;
import cl.teatromoro.promociones.model.Promocion;
import cl.teatromoro.promociones.service.PromocionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promociones")
@RequiredArgsConstructor
public class PromocionController {

    private final PromocionService service;

    // ==========================================
    // ENDPOINTS PARA CAMPAÑAS (CONEXIÓN A KAFKA)
    // ==========================================

    @PostMapping("/campanas")
    @ResponseStatus(HttpStatus.CREATED)
    public CampanaResponse saveCampana(@RequestBody CampanaRequest request) {
        // Al llamar a este método, el Service guardará en DB 
        // y automáticamente disparará el evento a Kafka.
        return service.guardarCampana(request);
    }

    @GetMapping("/campanas")
    public List<CampanaResponse> getCampanas() {
        return service.listarCampanas();
    }

    @DeleteMapping("/campanas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCampana(@PathVariable Long id) {
        // Este método también dispara el evento 'Deleted' a Kafka.
        service.eliminarCampana(id);
    }

    // ==========================================
    // OTROS ENDPOINTS (SOLO DB LOCAL)
    // ==========================================

    @GetMapping("/cupones")
    public List<Promocion> getPromos() { 
        return service.listarPromociones(); 
    }

    @PostMapping("/cupones")
    public Promocion savePromo(@RequestBody Promocion p) { 
        return service.guardarPromocion(p); 
    }

    @GetMapping("/lealtad")
    public List<ProgramaLealtad> getLealtad() { 
        return service.listarLealtad(); 
    }

    @PostMapping("/lealtad")
    public ProgramaLealtad saveLealtad(@RequestBody ProgramaLealtad pl) { 
        return service.guardarLealtad(pl); 
    }
}