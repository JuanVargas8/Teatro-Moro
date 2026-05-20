package cl.teatromoro.informes.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import cl.teatromoro.informes.dto.SnapshotVentasRequest;
import cl.teatromoro.informes.dto.SnapshotVentasResponse;
import cl.teatromoro.informes.exception.ResourceNotFoundException;
import cl.teatromoro.informes.exception.RecursoDuplicadoException;
import cl.teatromoro.informes.mapper.SnapshotVentasMapper;
import cl.teatromoro.informes.model.entity.SnapshotVentas;
import cl.teatromoro.informes.repository.SnapshotVentasRepository;

@Service
@RequiredArgsConstructor
public class SnapshotVentasService {

    private final SnapshotVentasRepository repository;
    private final SnapshotVentasMapper mapper;

    //---------L I S T A R ------------------------------------------
    public List<SnapshotVentasResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    //----------OBTENER POR ID------------------------------------------

    public SnapshotVentasResponse obtenerPorId(Long id) {
        SnapshotVentas snapshot = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SnapshotVentas", id));
        return mapper.toResponse(snapshot);
    }

    //-------------GUARDAR------------------------------------------------

    public SnapshotVentasResponse guardar(SnapshotVentasRequest request) {
        if (repository.existsByFecha(request.getFecha())) {
            throw new RecursoDuplicadoException("SnapshotVentas", "fecha", request.getFecha().toString());
        }
        SnapshotVentas snapshot = mapper.toEntity(request);
        return mapper.toResponse(repository.save(snapshot));
    }

    //--------------ELIMINAR POR ID---------------------------------------------
    
    public void eliminar(Long id) {
        SnapshotVentas snapshot = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SnapshotVentas", id));
        repository.delete(snapshot);
    }
}
