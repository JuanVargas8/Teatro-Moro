package cl.teatromoro.promociones.service;

import cl.teatromoro.promociones.model.Campana;
import cl.teatromoro.promociones.repository.CampanaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampanaService {

    private final CampanaRepository repository;

    public Campana guardar(Campana campana) {
        return repository.save(campana);
    }

    public List<Campana> listarTodas() {
        return repository.findAll();
    }
}