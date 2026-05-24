package cl.teatromoro.pagos.service;

import java.util.List;
import org.springframework.stereotype.Service;
import cl.teatromoro.pagos.model.Transaccion;
import cl.teatromoro.pagos.model.MetodoPagoUsuario;
import cl.teatromoro.pagos.model.Reembolso;
import cl.teatromoro.pagos.repository.TransaccionRepository;
import cl.teatromoro.pagos.repository.MetodoPagoRepository;
import cl.teatromoro.pagos.repository.ReembolsoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final ReembolsoRepository reembolsoRepository;

    // --- LÓGICA PARA TRANSACCIONES ---
    public List<Transaccion> listarTransacciones() {
        return transaccionRepository.findAll();
    }

    public Transaccion guardarTransaccion(Transaccion t) {
        return transaccionRepository.save(t);
    }

    // --- LÓGICA PARA MÉTODOS DE PAGO ---
    public MetodoPagoUsuario guardarMetodoPago(MetodoPagoUsuario metodo) {
        return metodoPagoRepository.save(metodo);
    }

    // --- LÓGICA PARA REEMBOLSOS ---
    public Reembolso procesarReembolso(Reembolso reembolso) {
        return reembolsoRepository.save(reembolso);
    }
}
    
