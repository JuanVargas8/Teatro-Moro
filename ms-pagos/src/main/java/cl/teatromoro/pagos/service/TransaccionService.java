package cl.teatromoro.pagos.service;

import java.util.List;
import org.springframework.stereotype.Service;

import cl.teatromoro.common.event.TransaccionCreatedEvent;
import cl.teatromoro.pagos.kafka.PagosEventProducer; 
import cl.teatromoro.pagos.model.Transaccion;
import cl.teatromoro.pagos.model.MetodoPagoUsuario;
import cl.teatromoro.pagos.model.Reembolso;
import cl.teatromoro.pagos.repository.TransaccionRepository;
import cl.teatromoro.pagos.repository.MetodoPagoRepository;
import cl.teatromoro.pagos.repository.ReembolsoRepository;
import cl.teatromoro.pagos.client.ReservaClient; 
import cl.teatromoro.pagos.exception.ResourceNotFoundException; 

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import feign.FeignException;

@Slf4j 
@Service
@RequiredArgsConstructor
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final ReembolsoRepository reembolsoRepository;
    private final PagosEventProducer pagosEventProducer;
    private final ReservaClient reservaClient; 

    // --- LÓGICA PARA TRANSACCIONES ---
    
    public List<Transaccion> listarTransacciones() {
        log.info("[LISTAR] Obteniendo todas las transacciones de la base de datos");
        return transaccionRepository.findAll();
    }

    public Transaccion guardarTransaccion(Transaccion t) {
        log.info("[CREACIÓN] Iniciando validación para cobrar el pedido (Bloqueo) ID: {}", t.getIdPedido());

        // 1. VALIDACIÓN CON FEIGN CLIENT
        try {
            reservaClient.obtenerBloqueoPorId(t.getIdPedido());
            log.info("Validación exitosa: El pedido/bloqueo ID {} existe en ms-reserva.", t.getIdPedido());
        } catch (FeignException.NotFound e) {
            log.error("Error de validación: No se encontró el pedido ID {} en ms-reserva.", t.getIdPedido());
            throw new ResourceNotFoundException("Pedido/Bloqueo", t.getIdPedido());
        } catch (FeignException e) {
            log.error("Error al comunicarse con ms-reserva: {}", e.getMessage());
            throw new RuntimeException("Error de comunicación con el servicio de reservas");
        }

        // 2. GUARDADO EN BASE DE DATOS Y LOG
        Transaccion saved = transaccionRepository.save(t);
        log.info("[CREACIÓN] Transacción guardada exitosamente en BD con ID: {}", saved.getId());

        // 3. ENVÍO DE EVENTO KAFKA Y LOG
        TransaccionCreatedEvent evento = new TransaccionCreatedEvent(
            saved.getId(),
            saved.getIdPedido(),
            saved.getMonto(),
            saved.getMetodoPago(),
            saved.getEstado()
        );
        pagosEventProducer.enviarTransaccionCreada(evento);
        log.info("[KAFKA] Evento TransaccionCreatedEvent enviado con éxito para transacción ID: {}", saved.getId());

        return saved;
    }

    // --- LÓGICA PARA MÉTODOS DE PAGO ---
    
    public MetodoPagoUsuario guardarMetodoPago(MetodoPagoUsuario metodo) {
        log.info("[CREACIÓN] Guardando nuevo método de pago para usuario ID: {}", metodo.getIdUsuario());
        return metodoPagoRepository.save(metodo);
    }

    // --- LÓGICA PARA REEMBOLSOS ---
    
    public Reembolso procesarReembolso(Reembolso reembolso) {
        log.info("[REEMBOLSO] Procesando reembolso para la transacción ID: {}", reembolso.getIdTransaccion());
        return reembolsoRepository.save(reembolso);
    }
}