package cl.teatromoro.catalogo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.catalogo.entity.Categoria;
import cl.teatromoro.catalogo.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Categoria obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    public Categoria guardar(Categoria categoria) {
        return repository.save(categoria);
    }

    public Categoria actualizar(Long id, Categoria categoria) {
        Categoria existente = obtenerPorId(id);

        existente.setNombre(categoria.getNombre());
        existente.setDescripcion(categoria.getDescripcion());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
