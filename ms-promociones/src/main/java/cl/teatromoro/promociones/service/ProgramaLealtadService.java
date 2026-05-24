package cl.teatromoro.promociones.service;

import cl.teatromoro.promociones.model.ProgramaLealtad;
import cl.teatromoro.promociones.repository.ProgramaLealtadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramaLealtadService {

    private final ProgramaLealtadRepository repository;

    public ProgramaLealtad guardar(ProgramaLealtad programa) {
        return repository.save(programa);
    }
}