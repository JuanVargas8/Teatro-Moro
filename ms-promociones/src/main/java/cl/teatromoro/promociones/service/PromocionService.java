package cl.teatromoro.promociones.service;

import cl.teatromoro.promociones.model.Campana;
import cl.teatromoro.promociones.model.ProgramaLealtad;
import cl.teatromoro.promociones.model.Promocion;
import cl.teatromoro.promociones.repository.CampanaRepository;
import cl.teatromoro.promociones.repository.ProgramaLealtadRepository;
import cl.teatromoro.promociones.repository.PromocionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromocionService {

    // Aquí llamamos a los 3 repositorios que creaste en el Paso 2
    private final PromocionRepository promocionRepo;
    private final CampanaRepository campanaRepo;
    private final ProgramaLealtadRepository lealtadRepo;

    // --- LÓGICA PARA CUPONES ---
    public List<Promocion> listarPromociones() { 
        return promocionRepo.findAll(); 
    }
    public Promocion guardarPromocion(Promocion p) { 
        return promocionRepo.save(p); 
    }

    // --- LÓGICA PARA CAMPAÑAS ---
    public List<Campana> listarCampanas() { 
        return campanaRepo.findAll(); 
    }
    public Campana guardarCampana(Campana c) { 
        return campanaRepo.save(c); 
    }

    // --- LÓGICA PARA PROGRAMA DE LEALTAD ---
    public List<ProgramaLealtad> listarLealtad() { 
        return lealtadRepo.findAll(); 
    }
    public ProgramaLealtad guardarLealtad(ProgramaLealtad pl) { 
        return lealtadRepo.save(pl); 
    }
}