package cl.teatromoro.catalogo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.catalogo.entity.MultimediaObra;
import cl.teatromoro.catalogo.repository.ObraMultimediaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MultimediaObraService {

    private final ObraMultimediaRepository repository;

    public List<MultimediaObra> listar() {
        return repository.findAll();
    }

    public MultimediaObra obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Multimedia no encontrada"));
    }

    public MultimediaObra guardar(MultimediaObra multimedia) {
        return repository.save(multimedia);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<MultimediaObra> porObra(Long obraId) {
        return repository.findByObraId(obraId);
    }

    public List<MultimediaObra> porTipo(String tipo) {
        return repository.findByTipo(tipo);
    }
}
