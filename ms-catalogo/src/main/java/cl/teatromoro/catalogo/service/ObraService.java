package cl.teatromoro.catalogo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.catalogo.entity.Obra;
import cl.teatromoro.catalogo.repository.ObraRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObraService {

    private final ObraRepository repository;

    public List<Obra> listar() {
        return repository.findAll();
    }

    public Obra obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra no encontrada"));
    }

    public Obra guardar(Obra obra) {
        return repository.save(obra);
    }

    public Obra actualizar(Long id, Obra obra) {
        Obra existente = obtenerPorId(id);

        existente.setTitulo(obra.getTitulo());
        existente.setSinopsis(obra.getSinopsis());
        existente.setDuracion(obra.getDuracion());
        existente.setClasificacionEdad(obra.getClasificacionEdad());
        existente.setCategoria(obra.getCategoria());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }


    public List<Obra> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Obra> porCategoria(Long categoriaId) {
        return repository.findByCategoriaId(categoriaId);
    }

    public List<Obra> largas(int minutos) {
        return repository.findByDuracionGreaterThan(minutos);
    }
}
