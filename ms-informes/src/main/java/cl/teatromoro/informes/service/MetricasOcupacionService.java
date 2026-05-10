package cl.teatromoro.informes.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import cl.teatromoro.informes.dto.MetricasOcupacionRequest;
import cl.teatromoro.informes.dto.MetricasOcupacionResponse;
import cl.teatromoro.informes.exception.ResourceNotFoundException;
import cl.teatromoro.informes.exception.RecursoDuplicadoException;
import cl.teatromoro.informes.mapper.MetricasOcupacionMapper;
import cl.teatromoro.informes.model.entity.MetricasOcupacion;
import cl.teatromoro.informes.repository.MetricasOcupacionRepository;

@Service
@RequiredArgsConstructor
public class MetricasOcupacionService {

    private final MetricasOcupacionRepository repository;
    private final MetricasOcupacionMapper mapper;

    public List<MetricasOcupacionResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public MetricasOcupacionResponse obtenerPorId(Long id) {
        MetricasOcupacion metricas = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MetricasOcupacion", id));
        return mapper.toResponse(metricas);
    }

    public MetricasOcupacionResponse guardar(MetricasOcupacionRequest request) {
        if (repository.existsByIdFuncion(request.getIdFuncion())) {
            throw new RecursoDuplicadoException("MetricasOcupacion", "idFuncion", request.getIdFuncion().toString());
        }
        MetricasOcupacion metricas = mapper.toEntity(request);
        return mapper.toResponse(repository.save(metricas));
    }

    public void eliminar(Long id) {
        MetricasOcupacion metricas = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MetricasOcupacion", id));
        repository.delete(metricas);
    }
}
