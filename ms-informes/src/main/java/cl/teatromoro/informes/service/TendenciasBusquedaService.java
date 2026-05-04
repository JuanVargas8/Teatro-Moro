package cl.teatromoro.informes.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import cl.teatromoro.informes.dto.TendenciasBusquedaRequest;
import cl.teatromoro.informes.dto.TendenciasBusquedaResponse;
import cl.teatromoro.informes.exception.ResourceNotFoundException;
import cl.teatromoro.informes.exception.RecursoDuplicadoException;
import cl.teatromoro.informes.mapper.TendenciasBusquedaMapper;
import cl.teatromoro.informes.model.entity.TendenciasBusqueda;
import cl.teatromoro.informes.repository.TendenciasBusquedaRepository;

@Service
@RequiredArgsConstructor
public class TendenciasBusquedaService {

    private final TendenciasBusquedaRepository repository;
    private final TendenciasBusquedaMapper mapper;

    public List<TendenciasBusquedaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public TendenciasBusquedaResponse obtenerPorId(Long id) {
        TendenciasBusqueda tendencia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TendenciasBusqueda", id));
        return mapper.toResponse(tendencia);
    }

    public TendenciasBusquedaResponse guardar(TendenciasBusquedaRequest request) {
        if (repository.existsByTerminoBusquedaIgnoreCaseAndFecha(
                request.getTerminoBusqueda(), request.getFecha())) {
            throw new RecursoDuplicadoException("TendenciasBusqueda", "terminoBusqueda", request.getTerminoBusqueda());
        }
        TendenciasBusqueda tendencia = mapper.toEntity(request);
        return mapper.toResponse(repository.save(tendencia));
    }

    public void eliminar(Long id) {
        TendenciasBusqueda tendencia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TendenciasBusqueda", id));
        repository.delete(tendencia);
    }
}
