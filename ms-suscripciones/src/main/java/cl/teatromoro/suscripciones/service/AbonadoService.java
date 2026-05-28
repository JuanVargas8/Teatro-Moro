package cl.teatromoro.suscripciones.service;
import feign.FeignException;
import cl.teatromoro.common.event.AbonadoCreatedEvent;
import cl.teatromoro.suscripciones.event.AbonadoEventProducer;
import cl.teatromoro.suscripciones.model.Abonado;
import cl.teatromoro.suscripciones.repository.AbonadoRepository;
import cl.teatromoro.suscripciones.client.UsuarioClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AbonadoService {

    private final AbonadoRepository repository;
    private final UsuarioClient usuarioClient;
    private final AbonadoEventProducer eventProducer;

    public AbonadoService(AbonadoRepository repository, UsuarioClient usuarioClient, AbonadoEventProducer eventProducer) {
        this.repository = repository;
        this.usuarioClient = usuarioClient;
        this.eventProducer = eventProducer;
    }

    public Abonado crear(Abonado abonado) {
        try {
            // Llama a ms-usuarios
            usuarioClient.obtenerUsuario(abonado.getUsuarioId());
        } catch (FeignException.NotFound e) {
            // cuando usuarios devuelve 404
            throw new RuntimeException("Usuario no existe");
        } catch (FeignException e) {
            // otros errores de comunicación
            throw new RuntimeException("Error al validar usuario");
        }

        abonado.setFechaInicio(LocalDate.now());
        Abonado guardado = repository.save(abonado);
        
        AbonadoCreatedEvent event = new AbonadoCreatedEvent();
        event.setId(guardado.getId());
        event.setUsuarioId(guardado.getUsuarioId());
        event.setPlanId(guardado.getPlanId());
        event.setFechaInicio(guardado.getFechaInicio());
        event.setFechaFin(guardado.getFechaFin());
        eventProducer.sendCreated(event);
        
        return guardado;
    }

    public List<Abonado> listar() {
        return repository.findAll();
    }

    public List<Abonado> porUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }
}