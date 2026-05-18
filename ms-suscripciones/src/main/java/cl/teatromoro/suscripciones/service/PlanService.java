package cl.teatromoro.suscripciones.service;

import cl.teatromoro.suscripciones.dto.PlanDTO;
import cl.teatromoro.suscripciones.dto.PlanResponseDTO;
import cl.teatromoro.suscripciones.exception.ResourceNotFoundException;
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

    public PlanResponseDTO crear(PlanDTO dto) {

        Plan plan = new Plan();

        plan.setNombre(dto.getNombre());
        plan.setPrecio(dto.getPrecio());
        plan.setBeneficios(dto.getBeneficios());

        return new PlanResponseDTO(repository.save(plan));
    }

    public List<PlanResponseDTO> listar() {

        return repository.findAll()
                .stream()
                .map(PlanResponseDTO::new)
                .toList();
    }

    public Plan obtenerEntidad(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Plan no encontrado"));
    }

    public PlanResponseDTO obtener(Long id) {

        return new PlanResponseDTO(obtenerEntidad(id));
    }

    public PlanResponseDTO actualizar(Long id, PlanDTO dto) {

        Plan existente = obtenerEntidad(id);

        existente.setNombre(dto.getNombre());
        existente.setPrecio(dto.getPrecio());
        existente.setBeneficios(dto.getBeneficios());

        return new PlanResponseDTO(repository.save(existente));
    }

    public void eliminar(Long id) {

        repository.deleteById(id);
    }
}