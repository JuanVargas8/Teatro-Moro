package cl.teatromoro.promociones.service;

import cl.teatromoro.promociones.model.Promocion;
import cl.teatromoro.promociones.repository.PromocionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromocionService {

    private final PromocionRepository repository;

    public Promocion guardar(Promocion promocion) {
        return repository.save(promocion);
    }

    public List<Promocion> listarTodas() {
        return repository.findAll();
    }
}