package cl.teatromoro.gestion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.gestion.dto.MantenimientoSalaRequest;
import cl.teatromoro.gestion.dto.MantenimientoSalaResponse;
import cl.teatromoro.gestion.exception.ResourceNotFoundException;
import cl.teatromoro.gestion.mapper.MantenimientoSalaMapper;
import cl.teatromoro.gestion.model.entity.MantenimientoSala;
import cl.teatromoro.gestion.model.entity.Sala;
import cl.teatromoro.gestion.repository.MantenimientoSalaRepository;
import cl.teatromoro.gestion.repository.SalaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MantenimientoSalaService {

    private final MantenimientoSalaRepository repository;
    private final SalaRepository salaRepository;
    private final MantenimientoSalaMapper mapper;

    public List<MantenimientoSalaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public MantenimientoSalaResponse obtenerPorId(Long id) {
        MantenimientoSala mantenimiento = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MantenimientoSala", id));

        return mapper.toResponse(mantenimiento);
    }

    public MantenimientoSalaResponse guardar(MantenimientoSalaRequest request) {

        Sala sala = salaRepository.findById(request.getSalaId())
                .orElseThrow(() -> new ResourceNotFoundException("Sala", request.getSalaId()));

        MantenimientoSala mantenimiento = mapper.toEntity(request, sala);

        return mapper.toResponse(repository.save(mantenimiento));
    }

    public void eliminar(Long id) {
        MantenimientoSala mantenimiento = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MantenimientoSala", id));

        repository.delete(mantenimiento);
    }

    public List<MantenimientoSalaResponse> porSala(Long salaId) {

        if (!salaRepository.existsById(salaId)) {
            throw new ResourceNotFoundException("Sala", salaId);
        }

        return repository.findBySalaId(salaId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
