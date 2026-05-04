package cl.teatromoro.pagos.service;

import cl.teatromoro.pagos.model.MetodoPagoUsuario;

import cl.teatromoro.pagos.repository.MetodoPagoRepository; 
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetodoPagoUsuarioService {

    // 2. Usamos el nombre correcto aquí también
    private final MetodoPagoRepository repository;

    public MetodoPagoUsuario guardar(MetodoPagoUsuario metodo) {
        return repository.save(metodo);
    }

    // Nota: Si 'findByIdUsuario' sigue saliendo en rojo, es porque 
    // debes agregar ese método dentro de tu MetodoPagoRepository.java
    public List<MetodoPagoUsuario> listarPorUsuario(Long idUsuario) {
        return repository.findByIdUsuario(idUsuario); 
    }
}