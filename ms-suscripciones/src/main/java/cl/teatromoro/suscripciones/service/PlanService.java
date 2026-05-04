package cl.teatromoro.suscripciones.service;

import cl.teatromoro.suscripciones.model.Plan;
import cl.teatromoro.suscripciones.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    private final PlanRepository repository;

    public PlanService(PlanRepository repository) {
        this.repository = repository;
    }

    public Plan crear(Plan plan) {
        return repository.save(plan);
    }

    public List<Plan> listar() {
        return repository.findAll();
    }

    public Plan obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));
    }

    public Plan actualizar(Long id, Plan plan) {
        Plan existente = obtener(id);
        existente.setNombre(plan.getNombre());
        existente.setPrecio(plan.getPrecio());
        existente.setBeneficios(plan.getBeneficios());
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}