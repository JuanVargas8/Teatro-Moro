package cl.teatromoro.personal.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import cl.teatromoro.personal.dto.PersonalRequest;
import cl.teatromoro.personal.dto.PersonalResponse;
import cl.teatromoro.personal.exception.ResourceNotFoundException;
import cl.teatromoro.personal.exception.RecursoDuplicadoException;
import cl.teatromoro.personal.mapper.PersonalMapper;
import cl.teatromoro.personal.model.entity.Personal;
import cl.teatromoro.personal.repository.PersonalRepository;

@Service
@RequiredArgsConstructor
public class PersonalService {

    private final PersonalRepository repository;
    private final PersonalMapper mapper;

    public List<PersonalResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public PersonalResponse obtenerPorId(Long id) {
        Personal entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personal", id));
        return mapper.toResponse(entity);
    }

    public PersonalResponse guardar(PersonalRequest request) {
        if (repository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new RecursoDuplicadoException("Personal", "nombre", request.getNombre());
        }
        Personal entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    public void eliminar(Long id) {
        Personal entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personal", id));
        repository.delete(entity);
    }
}
