package cl.teatromoro.personal.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import cl.teatromoro.personal.dto.ElencosObraRequest;
import cl.teatromoro.personal.dto.ElencosObraResponse;
import cl.teatromoro.personal.exception.ResourceNotFoundException;
import cl.teatromoro.personal.exception.RecursoDuplicadoException;
import cl.teatromoro.personal.mapper.ElencosObraMapper;
import cl.teatromoro.personal.model.entity.ElencosObra;
import cl.teatromoro.personal.repository.ElencosObraRepository;

@Service
@RequiredArgsConstructor
public class ElencosObraService {

    private final ElencosObraRepository repository;
    private final ElencosObraMapper mapper;

    public List<ElencosObraResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ElencosObraResponse obtenerPorId(Long id) {
        ElencosObra entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ElencosObra", id));
        return mapper.toResponse(entity);
    }

    public ElencosObraResponse guardar(ElencosObraRequest request) {
        if (repository.existsByIdObraAndIdPersonal(request.getIdObra(), request.getIdPersonal())) {
            throw new RecursoDuplicadoException("ElencosObra", "idObra/idPersonal", request.getIdObra() + "-" + request.getIdPersonal());
        }
        ElencosObra entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    public void eliminar(Long id) {
        ElencosObra entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ElencosObra", id));
        repository.delete(entity);
    }
}
