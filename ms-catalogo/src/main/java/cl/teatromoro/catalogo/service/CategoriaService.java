package cl.teatromoro.catalogo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.catalogo.dto.CategoriaRequest;
import cl.teatromoro.catalogo.dto.CategoriaResponse;
import cl.teatromoro.catalogo.exception.RecursoDuplicadoException;
import cl.teatromoro.catalogo.exception.ResourceNotFoundException;
import cl.teatromoro.catalogo.mapper.CategoriaMapper;
import cl.teatromoro.catalogo.model.entity.Categoria;
import cl.teatromoro.catalogo.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    // ─── LISTAR ─────────────────────────────────────────

    public List<CategoriaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER POR ID ─────────────────────────────────

    public CategoriaResponse obtenerPorId(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", id));

        return mapper.toResponse(categoria);
    }

    // ─── CREAR ──────────────────────────────────────────

    public CategoriaResponse guardar(CategoriaRequest request) {

        if (repository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new RecursoDuplicadoException("Categoría", "nombre", request.getNombre());
        }

        Categoria categoria = mapper.toEntity(request);

        return mapper.toResponse(repository.save(categoria));
    }

    // ─── ACTUALIZAR ─────────────────────────────────────

    public CategoriaResponse actualizar(Long id, CategoriaRequest request) {

        Categoria existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", id));

        // Validar duplicado si cambia el nombre
        if (!existente.getNombre().equalsIgnoreCase(request.getNombre()) &&
            repository.existsByNombreIgnoreCase(request.getNombre())) {

            throw new RecursoDuplicadoException("Categoría", "nombre", request.getNombre());
        }

        existente.setNombre(request.getNombre());
        existente.setDescripcion(request.getDescripcion());

        return mapper.toResponse(repository.save(existente));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", id));

        repository.delete(categoria);
    }
}
