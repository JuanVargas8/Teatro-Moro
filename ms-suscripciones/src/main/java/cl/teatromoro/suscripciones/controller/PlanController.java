package cl.teatromoro.suscripciones.controller;

import cl.teatromoro.suscripciones.model.Plan;
import cl.teatromoro.suscripciones.service.PlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planes")
public class PlanController {

    private final PlanService service;

    public PlanController(PlanService service) {
        this.service = service;
    }

    @PostMapping
    public Plan crear(@RequestBody Plan plan) {
        return service.crear(plan);
    }

    @GetMapping
    public List<Plan> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Plan obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PutMapping("/{id}")
    public Plan actualizar(@PathVariable Long id, @RequestBody Plan plan) {
        return service.actualizar(id, plan);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}