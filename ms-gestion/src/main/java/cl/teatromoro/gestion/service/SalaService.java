package cl.teatromoro.gestion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.gestion.exception.RecursoDuplicadoException;
import cl.teatromoro.gestion.dto.SalaRequest;
import cl.teatromoro.gestion.dto.SalaResponse;
import cl.teatromoro.gestion.exception.ResourceNotFoundException;
import cl.teatromoro.gestion.mapper.SalaMapper;
import cl.teatromoro.gestion.model.entity.Sala;
import cl.teatromoro.gestion.repository.SalaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository repository;
    private final SalaMapper mapper;

    public List<SalaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public SalaResponse obtenerPorId(Long id) {
        Sala sala = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala", id));

        return mapper.toResponse(sala);
    }

    public SalaResponse guardar(SalaRequest request) {

        if (repository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new RecursoDuplicadoException("Sala", "nombre", request.getNombre());
        }

        Sala sala = mapper.toEntity(request);
        return mapper.toResponse(repository.save(sala));
    }

    public SalaResponse actualizar(Long id, SalaRequest request) {

        Sala existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala", id));

        existente.setNombre(request.getNombre());
        existente.setCapacidadTotal(request.getCapacidadTotal());
        existente.setDescripcionTecnica(request.getDescripcionTecnica());

        return mapper.toResponse(repository.save(existente));
    }

    public void eliminar(Long id) {
        Sala sala = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala", id));

        repository.delete(sala);
    }
}
