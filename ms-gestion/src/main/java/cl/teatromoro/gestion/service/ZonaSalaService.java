package cl.teatromoro.gestion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.gestion.dto.ZonaSalaRequest;
import cl.teatromoro.gestion.dto.ZonaSalaResponse;
import cl.teatromoro.gestion.exception.RecursoDuplicadoException;
import cl.teatromoro.gestion.exception.ResourceNotFoundException;
import cl.teatromoro.gestion.mapper.ZonaSalaMapper;
import cl.teatromoro.gestion.model.entity.Sala;
import cl.teatromoro.gestion.model.entity.ZonaSala;
import cl.teatromoro.gestion.repository.SalaRepository;
import cl.teatromoro.gestion.repository.ZonaSalaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZonaSalaService {

    private final ZonaSalaRepository repository;
    private final SalaRepository salaRepository;
    private final ZonaSalaMapper mapper;

    public List<ZonaSalaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ZonaSalaResponse obtenerPorId(Long id) {
        ZonaSala zona = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ZonaSala", id));

        return mapper.toResponse(zona);
    }

    public ZonaSalaResponse guardar(ZonaSalaRequest request) {

        Sala sala = salaRepository.findById(request.getSalaId())
                .orElseThrow(() -> new ResourceNotFoundException("Sala", request.getSalaId()));

        if (repository.existsByNombreIgnoreCaseAndSalaId(
                request.getNombre(), request.getSalaId())) {

            throw new RecursoDuplicadoException(
                    "ZonaSala", "nombre", request.getNombre());
        }

        ZonaSala zona = mapper.toEntity(request, sala);
        return mapper.toResponse(repository.save(zona));
    }

    public void eliminar(Long id) {
        ZonaSala zona = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ZonaSala", id));

        repository.delete(zona);
    }

    public List<ZonaSalaResponse> porSala(Long salaId) {

        if (!salaRepository.existsById(salaId)) {
            throw new ResourceNotFoundException("Sala", salaId);
        }

        return repository.findBySalaId(salaId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
