package cl.teatromoro.promociones.service;

import cl.teatromoro.promociones.dto.CampanaRequest;
import cl.teatromoro.promociones.dto.CampanaResponse;
import cl.teatromoro.promociones.model.Campana;
import cl.teatromoro.promociones.model.ProgramaLealtad;
import cl.teatromoro.promociones.model.Promocion;
import cl.teatromoro.promociones.repository.CampanaRepository;
import cl.teatromoro.promociones.repository.ProgramaLealtadRepository;
import cl.teatromoro.promociones.repository.PromocionRepository;
import cl.teatromoro.promociones.mapper.CampanaMapper;
import cl.teatromoro.promociones.event.PromocionesEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromocionService {

    private final CampanaRepository campanaRepo;
    private final PromocionRepository promocionRepo;
    private final ProgramaLealtadRepository lealtadRepo;
    private final CampanaMapper campanaMapper;
    private final PromocionesEventProducer eventProducer;

    // --- MÉTODOS PARA CAMPAÑAS (Con DTOs) ---

    public CampanaResponse guardarCampana(CampanaRequest request) {
        Campana entidad = campanaMapper.toEntity(request);
        Campana guardada = campanaRepo.save(entidad);
        eventProducer.enviarCampanaCreada(campanaMapper.toEvent(guardada));
        return campanaMapper.toResponse(guardada);
    }

    public List<CampanaResponse> listarCampanas() {
        return campanaRepo.findAll().stream()
                .map(campanaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void eliminarCampana(Long id) {
        if (campanaRepo.existsById(id)) {
            campanaRepo.deleteById(id);
            eventProducer.enviarCampanaEliminada(id);
        } else {
            throw new RuntimeException("Error: No existe la campaña con ID: " + id);
        }
    }

    // --- MÉTODOS PARA CUPONES (Sin DTOs por ahora, para limpiar el error) ---

    public List<Promocion> listarPromociones() {
        return promocionRepo.findAll();
    }

    public Promocion guardarPromocion(Promocion p) {
        return promocionRepo.save(p);
    }

    // --- MÉTODOS PARA LEALTAD (Sin DTOs por ahora) ---

    public List<ProgramaLealtad> listarLealtad() {
        return lealtadRepo.findAll();
    }

    public ProgramaLealtad guardarLealtad(ProgramaLealtad pl) {
        return lealtadRepo.save(pl);
    }
}