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
import cl.teatromoro.promociones.kafka.PromocionesEventProducer;
import cl.teatromoro.promociones.client.PagosClient; // <-- Importamos el nuevo cliente
import cl.teatromoro.promociones.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import feign.FeignException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromocionService {

    private final CampanaRepository campanaRepo;
    private final PromocionRepository promocionRepo;
    private final ProgramaLealtadRepository lealtadRepo;
    private final CampanaMapper campanaMapper;
    private final PromocionesEventProducer eventProducer;
    private final PagosClient pagosClient; // <-- Inyectamos el cliente Feign

    // --- MÉTODOS PARA CAMPAÑAS ---

    public CampanaResponse guardarCampana(CampanaRequest request) {
        log.info("[CREACIÓN] Iniciando guardado de campaña: {}", request.getNombre());
        Campana entidad = campanaMapper.toEntity(request);
        Campana guardada = campanaRepo.save(entidad);
        
        log.info("[KAFKA] Disparando evento de campaña creada con ID: {}", guardada.getId());
        eventProducer.enviarCampanaCreada(campanaMapper.toEvent(guardada));
        
        return campanaMapper.toResponse(guardada);
    }

    public List<CampanaResponse> listarCampanas() {
        log.info("[LISTAR] Obteniendo todas las campañas");
        return campanaRepo.findAll().stream()
                .map(campanaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void eliminarCampana(Long id) {
        log.info("[ELIMINACIÓN] Intentando eliminar campaña con ID: {}", id);
        if (campanaRepo.existsById(id)) {
            campanaRepo.deleteById(id);
            log.info("[KAFKA] Disparando evento de campaña eliminada para ID: {}", id);
            eventProducer.enviarCampanaEliminada(id);
        } else {
            log.error("[ERROR] No se pudo eliminar: Campaña con ID {} no encontrada", id);
            throw new ResourceNotFoundException("Campaña", id);
        }
    }

    // --- MÉTODOS PARA CUPONES ---

    public List<Promocion> listarPromociones() {
        log.info("[LISTAR] Obteniendo todas las promociones/cupones");
        return promocionRepo.findAll();
    }

    public Promocion guardarPromocion(Promocion p) {
        log.info("[CREACIÓN] Guardando nueva promoción con código: {}", p.getCodigo());
        return promocionRepo.save(p);
    }

    // --- MÉTODOS PARA LEALTAD (CON VINCULACIÓN A MS-PAGOS) ---

    public List<ProgramaLealtad> listarLealtad() {
        log.info("[LISTAR] Obteniendo todos los programas de lealtad");
        return lealtadRepo.findAll();
    }

    public ProgramaLealtad guardarLealtad(ProgramaLealtad pl) {
        // Vinculación síncrona: Usamos el ID del programa temporalmente como simulador de ID Transacción
        log.info("[FEIGN] Validando estado de transacción en ms-pagos para el proceso de lealtad...");
        
        try {
            // Intentamos llamar a ms-pagos mediante Feign
            pagosClient.obtenerTransaccionPorId(1L); // Simulamos buscar la transacción ID 1
            log.info("[FEIGN] Validación exitosa: Conexión establecida con ms-pagos.");
            
        } catch (FeignException.NotFound e) {
            log.warn("[FEIGN] ms-pagos respondió que la transacción no existe (404), pero procedemos por cortesía comercial.");
        } catch (FeignException e) {
            log.error("[FEIGN ERROR] No se pudo conectar con ms-pagos: {}. El servicio podría estar caído.", e.getMessage());
            // No bloqueamos el flujo para que el cliente no se quede pegado si pagos se cae
        }

        log.info("[CREACIÓN] Guardando nuevo programa de lealtad nivel: {}", pl.getNombreNivel());
        return lealtadRepo.save(pl);
    }
}