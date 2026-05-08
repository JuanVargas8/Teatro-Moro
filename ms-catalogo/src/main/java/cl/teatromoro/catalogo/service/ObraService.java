package cl.teatromoro.catalogo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.catalogo.dto.ObraRequest;
import cl.teatromoro.catalogo.dto.ObraResponse;
import cl.teatromoro.common.exception.DuplicateResourceException;
import cl.teatromoro.common.exception.EntityNotFoundException;
import cl.teatromoro.catalogo.mapper.ObraMapper;
import cl.teatromoro.catalogo.model.entity.Categoria;
import cl.teatromoro.catalogo.model.entity.Obra;
import cl.teatromoro.catalogo.repository.CategoriaRepository;
import cl.teatromoro.catalogo.repository.ObraRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObraService {

    private final ObraRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final ObraMapper mapper;

    // ─── LISTAR ─────────────────────────────────────────

    public List<ObraResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER POR ID ─────────────────────────────────

    public ObraResponse obtenerPorId(Long id) {
        Obra obra = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Obra", "id", id));

        return mapper.toResponse(obra);
    }

    // ─── CREAR ──────────────────────────────────────────

    public ObraResponse guardar(ObraRequest request) {

        if (repository.existsByTitulo(request.getTitulo())) {
            throw new DuplicateResourceException("Obra", "título", request.getTitulo(), "El título ya está en uso");
        }

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría", "id", request.getCategoriaId()));

        Obra obra = mapper.toEntity(request, categoria);

        return mapper.toResponse(repository.save(obra));
    }

    // ─── ACTUALIZAR ─────────────────────────────────────

    public ObraResponse actualizar(Long id, ObraRequest request) {

        Obra existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Obra", "id", id));

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría", "id", request.getCategoriaId()));

        // Validación de duplicado si cambia el título
        if (!existente.getTitulo().equals(request.getTitulo()) &&
            repository.existsByTitulo(request.getTitulo())) {

            throw new DuplicateResourceException("Obra", "título", request.getTitulo(), "El título ya está en uso");
        }

        existente.setTitulo(request.getTitulo());
        existente.setSinopsis(request.getSinopsis());
        existente.setDuracion(request.getDuracion());
        existente.setClasificacionEdad(request.getClasificacionEdad());
        existente.setCategoria(categoria);

        return mapper.toResponse(repository.save(existente));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        Obra obra = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Obra", "id", id));

        repository.delete(obra);
    }

    // ─── BUSCAR POR TÍTULO ─────────────────────────────

    public List<ObraResponse> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── POR CATEGORÍA ─────────────────────────────────

    public List<ObraResponse> porCategoria(Long categoriaId) {

        if (!categoriaRepository.existsById(categoriaId)) {
            throw new EntityNotFoundException("Categoría", "id", categoriaId);
        }

        return repository.findByCategoriaId(categoriaId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBRAS LARGAS ──────────────────────────────────

    public List<ObraResponse> largas(int minutos) {
        return repository.findByDuracionGreaterThan(minutos)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
