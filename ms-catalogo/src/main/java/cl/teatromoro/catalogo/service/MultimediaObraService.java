package cl.teatromoro.catalogo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.teatromoro.catalogo.dto.MultimediaObraRequest;
import cl.teatromoro.catalogo.dto.MultimediaObraResponse;
import cl.teatromoro.catalogo.exception.ResourceNotFoundException;
import cl.teatromoro.catalogo.mapper.MultimediaObraMapper;
import cl.teatromoro.catalogo.model.entity.MultimediaObra;
import cl.teatromoro.catalogo.model.entity.Obra;
import cl.teatromoro.catalogo.repository.ObraMultimediaRepository;
import cl.teatromoro.catalogo.repository.ObraRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MultimediaObraService {

    private final ObraMultimediaRepository repository;
    private final ObraRepository obraRepository;
    private final MultimediaObraMapper mapper;

    // ─── LISTAR ─────────────────────────────────────────

    public List<MultimediaObraResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── OBTENER POR ID ─────────────────────────────────

    public MultimediaObraResponse obtenerPorId(Long id) {
        MultimediaObra multimedia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Multimedia", id));

        return mapper.toResponse(multimedia);
    }

    // ─── CREAR ──────────────────────────────────────────

    public MultimediaObraResponse guardar(MultimediaObraRequest request) {

        Obra obra = obraRepository.findById(request.getObraId())
                .orElseThrow(() -> new ResourceNotFoundException("Obra", request.getObraId()));

        MultimediaObra multimedia = mapper.toEntity(request, obra);

        return mapper.toResponse(repository.save(multimedia));
    }

    // ─── ELIMINAR ───────────────────────────────────────

    public void eliminar(Long id) {
        MultimediaObra multimedia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Multimedia", id));

        repository.delete(multimedia);
    }

    // ─── POR OBRA ───────────────────────────────────────

    public List<MultimediaObraResponse> porObra(Long obraId) {

        if (!obraRepository.existsById(obraId)) {
            throw new ResourceNotFoundException("Obra", obraId);
        }

        return repository.findByObraId(obraId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ─── POR TIPO ───────────────────────────────────────

    public List<MultimediaObraResponse> porTipo(String tipo) {
        return repository.findByTipo(tipo)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
