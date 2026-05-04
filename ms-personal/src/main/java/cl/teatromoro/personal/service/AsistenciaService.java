package cl.teatromoro.personal.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import cl.teatromoro.personal.dto.AsistenciaRequest;
import cl.teatromoro.personal.dto.AsistenciaResponse;
import cl.teatromoro.personal.exception.ResourceNotFoundException;
import cl.teatromoro.personal.exception.RecursoDuplicadoException;
import cl.teatromoro.personal.mapper.AsistenciaMapper;
import cl.teatromoro.personal.model.entity.Asistencia;
import cl.teatromoro.personal.repository.AsistenciaRepository;

@Service
@RequiredArgsConstructor
public class AsistenciaService {

    private final AsistenciaRepository repository;
    private final AsistenciaMapper mapper;

    public List<AsistenciaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public AsistenciaResponse obtenerPorId(Long id) {
        Asistencia asistencia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia", id));
        return mapper.toResponse(asistencia);
    }

    public AsistenciaResponse guardar(AsistenciaRequest request) {
        if (repository.existsByIdPersonalAndIdFuncionAndHoraEntrada(
                request.getIdPersonal(), request.getIdFuncion(), request.getHoraEntrada())) {
            throw new RecursoDuplicadoException("Asistencia", "registro", request.getIdPersonal() + "-" + request.getIdFuncion());
        }
        Asistencia asistencia = mapper.toEntity(request);
        return mapper.toResponse(repository.save(asistencia));
    }

    public void eliminar(Long id) {
        Asistencia asistencia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia", id));
        repository.delete(asistencia);
    }
}
