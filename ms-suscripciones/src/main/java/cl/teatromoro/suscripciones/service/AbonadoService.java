package cl.teatromoro.suscripciones.service;
import feign.FeignException;
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

    public AbonadoService(AbonadoRepository repository, UsuarioClient usuarioClient) {
        this.repository = repository;
        this.usuarioClient = usuarioClient;
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
        return repository.save(abonado);
    }

    public List<Abonado> listar() {
        return repository.findAll();
    }

    public List<Abonado> porUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }
}