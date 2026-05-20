package cl.teatromoro.gestion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.gestion.dto.ZonaSalaRequest;
import cl.teatromoro.gestion.dto.ZonaSalaResponse;
import cl.teatromoro.common.exception.DuplicateResourceException;
import cl.teatromoro.common.exception.EntityNotFoundException;
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
                .orElseThrow(() -> new EntityNotFoundException("ZonaSala", "id", id));

        return mapper.toResponse(zona);
    }

    public ZonaSalaResponse guardar(ZonaSalaRequest request) {

        Sala sala = salaRepository.findById(request.getSalaId())
                .orElseThrow(() -> new EntityNotFoundException("Sala", "id", request.getSalaId()));

        if (repository.existsByNombreIgnoreCaseAndSalaId(
                request.getNombre(), request.getSalaId())) {

            throw new DuplicateResourceException(
                    "ZonaSala", "nombre", request.getNombre(), "Ya existe una zona con ese nombre en la sala");
        }

        ZonaSala zona = mapper.toEntity(request, sala);
        return mapper.toResponse(repository.save(zona));
    }

    public void eliminar(Long id) {
        ZonaSala zona = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ZonaSala", "id", id));

        repository.delete(zona);
    }

    public List<ZonaSalaResponse> porSala(Long salaId) {

        if (!salaRepository.existsById(salaId)) {
            throw new EntityNotFoundException("Sala", "id", salaId);
        }

        return repository.findBySalaId(salaId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
